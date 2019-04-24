package com.arjvik.robotics.ironreignbot.handlers.admin;

import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

@EventHandler
public class AdminShutdownHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if (content.equals("!admin shutdown")) {
			LoggerFactory.getLogger(getClass()).info("Shutting down client");;
			replyTo(msg, "Goodbye!");
			client.logout().block();
		}
	}

}
