package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public abstract class AbstractMessageHandler implements Handler {
	
	protected String prefix;
	
	@Override
	public void setCommandPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage().getContent().map(s -> s.startsWith(prefix)).orElse(false))
			  .subscribe(e -> onMessageEvent(e.getMessage(), e.getMessage().getContent().map(s -> s.substring(prefix.length()).trim()).orElse(null)));
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
