package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!admin clear-posts")
public class AdminClearPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		store.removeAllPosts();
		replyTo(msg, "Removed all blog posts");
	}

}
