package com.arjvik.robotics.ironreignbot.handlers.misc;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;

@EventHandler("!vote")
public class VoteHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		msg.addReaction(ReactionEmoji.unicode("👍")).block();
		msg.addReaction(ReactionEmoji.unicode("👎")).block();
	}

}
