package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!admin import-posts")
public class AdminImportPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (content.matches("(?s)\\s*```.*```\\s*")) {
			String json = content.replaceAll("(?s)\\s*```(.*)```\\s*", "$1").trim();
			if (store.importStore(json))
				replyTo(msg, "Successfully imported all blog posts");
			else
				replyTo(msg, "Error importing blog posts");
		} else {
			replyTo(msg, "Please add the JSON dump within triple backticks (\\`\\`\\`)");
		}
	}

}
