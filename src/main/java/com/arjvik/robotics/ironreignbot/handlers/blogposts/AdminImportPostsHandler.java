package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.EventHandler;
import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;

@EventHandler
public class AdminImportPostsHandler extends AbstractAdminHandler {

	@Override
	protected void onMessageEvent(Message msg, String content, DiscordClient client) {
		if (content.startsWith("!admin import-posts")) {
			if (content.replaceAll("\\s", "").matches(".*```.*```.*")) {
				String json = content.replaceAll("\\s", "").replaceAll(".*```(.*)```.*", "$1").trim();
				if (store.importStore(json))
					replyTo(msg, "Successfully imported all blog posts");
				else
					replyTo(msg, "Error importing blog posts");
			} else {
				replyTo(msg, "Please add the JSON dump within triple backticks (\\`\\`\\`)");
			}
		}
	}

}
