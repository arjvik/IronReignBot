package com.arjvik.robotics.ironreignbot.handlers.misc;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler
public class PingHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (content.equals("!ping"))
			replyTo(msg, "Pong!");
	}

}
