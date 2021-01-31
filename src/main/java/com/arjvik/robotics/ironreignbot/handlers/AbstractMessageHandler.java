package com.arjvik.robotics.ironreignbot.handlers;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Flux;

public abstract class AbstractMessageHandler implements Handler {
	
	public static int MAX_MESSAGE_LENGTH = 1900;
	
	protected String prefix;
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void setCommandPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public void setupRoute(GatewayDiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage()
					  		.getContent()
					  		.startsWith(prefix))
			  .subscribe(this::dispatchMessageCreateEvent);
	}
	
	protected void dispatchMessageCreateEvent(MessageCreateEvent e) {
		log.info("{} <@{}>: \"{}\"",
				e.getMessage().getAuthor().get().getUsername(),
				e.getMessage().getAuthor().get().getId().asLong(),
				e.getMessage().getContent());
		onMessageEvent(e.getMessage(), e.getMessage()
										.getContent()
										.substring(prefix.length())
			  						  	.trim());
	}
	
	protected abstract void onMessageEvent(Message msg, String content);
	
	private static Collection<String> splitText(String text) {
		String[] lines = text.split("(?<=\n)");
		Deque<String> split = new ArrayDeque<>();
		split.push("");
		for (String line : lines) {
			if ((split.getLast() + line).length() <= MAX_MESSAGE_LENGTH)
				split.addLast(split.removeLast() + line);
			else if (line.length() <= MAX_MESSAGE_LENGTH)
				split.addLast(line);
			else
				for (int i = 0; i < line.length(); i += MAX_MESSAGE_LENGTH)
					split.addLast(String.format("%s%s%s%n",
							i == 0 ? "" : "...",
							line.substring(i, Math.min(i + MAX_MESSAGE_LENGTH, line.length())),
							i + MAX_MESSAGE_LENGTH >= line.length() ? "" : "..."));
		}
		return split;
	}
	
	protected List<Message> replyTo(Message message, String format, Object... args) {
		String text = String.format(format, args);
		log.info("Response: \"{}\"", text);
		Collection<String> split = splitText(text);
		if (split.size() > 1)
			for (String line : split)
				log.info("Split message: \"{}\"", line);
		return message.getChannel()
					  .flatMapMany(ch -> Flux.fromIterable(split)
											 .flatMap(ch::createMessage))
					  .collectList()
					  .block();
	}
	
	protected static long getIDFromMention(String mention) {
		return Long.parseLong(mention.replaceAll("<@!?(\\d+)>", "$1"));
	}
	
	protected static String formatMention(long id) {
		return String.format("<@!%d>", id);
	}
	
	protected static String formatMention(String mention) {
		return formatMention(getIDFromMention(mention));
	}
	
	protected static boolean isMention(String mention) {
		return mention.matches("<@!?\\d+>");
	}
	
	protected static long getAuthorID(Message message) {
		return message.getAuthor().get().getId().asLong();
	}

}
