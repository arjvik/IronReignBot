package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.time.LocalDate;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler
public class PostAssignHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (!content.startsWith("!blog assign"))
			return;
		String[] cmd = content.substring("!blog assign".length()).trim().split("\\s+", 2);
		if (cmd.length < 2 || !isMention(cmd[0]))
			replyTo(msg, "Invalid usage of `!blog assign <@user> <blog post topic>`");
		else {
			BlogPost post = new BlogPost(cmd[1], LocalDate.now());
			store.assignBlogPost(getIDFromMention(cmd[0]), post);
			replyTo(msg, "Assigned %s blog post \"%s\"", formatMention(cmd[0]), cmd[1]);
		}
	}

}
