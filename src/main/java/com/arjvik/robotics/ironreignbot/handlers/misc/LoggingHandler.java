package com.arjvik.robotics.ironreignbot.handlers.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!")
public class LoggingHandler extends AbstractMessageHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void onMessageEvent(Message msg, String content) {
		log.info("{} <@{}>: \"{}\"",
				msg.getAuthor().get().getUsername(),
				msg.getAuthor().get().getId().asLong(),
				msg.getContent().get());
	}

}