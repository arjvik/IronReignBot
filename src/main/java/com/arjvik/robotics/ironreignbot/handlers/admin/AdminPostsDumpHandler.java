package com.arjvik.robotics.ironreignbot.handlers.admin;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

public class AdminPostsDumpHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if(content.equals("!dump posts"))
			replyTo(msg, store.getAllBlogPosts().toString());
	}

}
