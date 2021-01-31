package com.arjvik.robotics.ironreignbot.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public abstract class AbstractMessageHandler implements Handler {
	
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
	
	protected static Message replyTo(Message message, String format, Object... args) {
		return message.getChannel().flatMap(ch -> ch.createMessage(String.format(format, args))).block();
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
