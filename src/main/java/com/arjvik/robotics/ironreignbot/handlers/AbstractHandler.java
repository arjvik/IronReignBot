package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.object.entity.Message;

public abstract class AbstractHandler implements Handler {

	protected static Message replyTo(Message message, String format, Object... args) {
		return message.getChannel().flatMap(ch -> ch.createMessage(String.format(format, args))).block();
	}
	
	protected static String formatMention(String mention) {
		return mention.replaceAll("<@!?(\\d+)>", "<@!\\1>");
	}
	
	protected static boolean isMention(String mention) {
		return mention.matches("<@!?\\d+>");
	}

}
