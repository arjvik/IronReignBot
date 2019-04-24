package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.util.List;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.handlers.AbstractImperativeHandler;

import discord4j.core.object.entity.Message;

public class PostListHandler extends AbstractImperativeHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (!content.startsWith("!blog list"))
			return;
		String cmd = content.substring("!blog list".length()).trim();
		long userID = 0;
		if (cmd.length() == 0)
			userID = msg.getAuthor().get().getId().asLong();
		else if (isMention(cmd))
			userID = getIDFromMention(cmd);
		else if (cmd.equals("all"))
			userID = 1;
		if (userID == 0) {
			replyTo(msg, "Invalid usage of `!blog list [<@user> | all]`");
		} else if (userID == 1) {
			for (long id : store.getAllBlogPosts().keySet()) {
				if (!store.getBlogPosts(id).isEmpty()) {
					replyTo(msg, "Blog posts assigned to %s:", formatMention(id));
					List<BlogPost> posts = store.getBlogPosts(id);
					for (int postID = 0; postID < posts.size(); postID++)
						replyTo(msg, "%d. %s", postID + 1, posts.get(postID));
				} else {
					replyTo(msg, "No blog posts assigned to %s", formatMention(id));
				}
			}
		} else if (store.getAllBlogPosts().containsKey(userID) && !store.getBlogPosts(userID).isEmpty()) {
			replyTo(msg, "Blog posts assigned to %s:", formatMention(userID));
			List<BlogPost> posts = store.getBlogPosts(userID);
			for (int postID = 0; postID < posts.size(); postID++)
				replyTo(msg, "%d. %s", postID + 1, posts.get(postID));
		} else {
			replyTo(msg, "No blog posts assigned to %s", formatMention(userID));
		}
	}

}
