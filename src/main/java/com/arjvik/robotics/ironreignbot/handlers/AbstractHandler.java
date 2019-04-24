package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.object.entity.Message;

public abstract class AbstractHandler implements Handler {

	protected static Message replyTo(Message message, String format, Object... args) {
		return message.getChannel().flatMap(ch -> ch.createMessage(String.format(format, args))).block();
	}
	
	protected static long getIDFromMention(String mention) {
		return Long.parseLong(mention.replaceAll("<@!?(\\d+)>", "\\1"));
		
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
