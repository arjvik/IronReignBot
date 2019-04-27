package com.arjvik.robotics.ironreignbot.handlers.admin;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

@EventHandler
public class AdminClearPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if (content.contentEquals("!admin clear-posts")) {
			store.removeAllPosts();
			replyTo(msg, "Removed all blog posts");
		}
	}

}
