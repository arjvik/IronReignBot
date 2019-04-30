package com.arjvik.robotics.ironreignbot.handlers.admin;

import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!admin shutdown")
public class AdminShutdownHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		LoggerFactory.getLogger(getClass()).info("Shutting down client");
		replyTo(msg, "Goodbye!");
		msg.getClient().logout().block();
	}

}
