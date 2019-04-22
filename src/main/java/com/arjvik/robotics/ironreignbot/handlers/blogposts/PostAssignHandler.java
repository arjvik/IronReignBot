package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.time.LocalDate;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.handlers.AbstractImperativeHandler;

import discord4j.core.object.entity.Message;

public class PostAssignHandler extends AbstractImperativeHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (!content.startsWith("!blog assign"))
			return;
		String[] cmd = content.substring("!blog assign".length()).trim().split("\\s+", 2);
		if (cmd.length < 2)
			replyTo(msg, "Invalid usage of `!blog assign <@user> <blog post topic>`");
		else if (!cmd[0].matches("<@\\d+>")) {
			replyTo(msg, "Invalid user: %s", cmd[0]);
		} else {
			BlogPost post = new BlogPost(cmd[1], LocalDate.now());
			store.assignBlogPost(cmd[0], post);
			replyTo(msg, "Assigned %s blog post \"%s\"", cmd[0], cmd[1]);
		}
	}

}
