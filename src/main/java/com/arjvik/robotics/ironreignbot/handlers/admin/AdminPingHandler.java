package com.arjvik.robotics.ironreignbot.handlers.admin;

import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

@EventHandler
public class AdminPingHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if (content.equals("!ping") || content.equals("!admin ping"))
			replyTo(msg, "Pong!");
	}

}
