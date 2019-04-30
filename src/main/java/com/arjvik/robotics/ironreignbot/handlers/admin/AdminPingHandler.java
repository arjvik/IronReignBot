package com.arjvik.robotics.ironreignbot.handlers.admin;

import com.arjvik.robotics.ironreignbot.handlers.annotations.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!ping")
@Disabled
public class AdminPingHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		replyTo(msg, "Pong!");
	}

}
