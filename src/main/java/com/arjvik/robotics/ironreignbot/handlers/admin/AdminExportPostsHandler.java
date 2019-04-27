package com.arjvik.robotics.ironreignbot.handlers.admin;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

@EventHandler
public class AdminExportPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if(content.equals("!admin export-posts"))
			replyTo(msg, "```\n%s\n```", store.exportStore());
	}

}
