package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!admin export-posts")
public class AdminExportPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		replyTo(msg, "```\n%s\n```", store.exportStore());
	}

}
