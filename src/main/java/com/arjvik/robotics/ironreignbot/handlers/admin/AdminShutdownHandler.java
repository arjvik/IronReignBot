package com.arjvik.robotics.ironreignbot.handlers.admin;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

public class AdminShutdownHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if (content.equals("!admin shutdown")) {
			replyTo(msg, "Goodbye!");
			client.logout().block();
		}
	}

}
