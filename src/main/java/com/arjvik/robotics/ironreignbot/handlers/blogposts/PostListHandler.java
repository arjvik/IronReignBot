package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.util.List;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!blog list")
public class PostListHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		long userID = 0;
		if (content.length() == 0)
			userID = msg.getAuthor().get().getId().asLong();
		else if (isMention(content))
			userID = getIDFromMention(content);
		else if (content.equals("all") || content.equals("@everyone"))
			userID = 1;
		
		if (userID == 0) {
			replyTo(msg, "Invalid usage of `!blog list [<@user> | all]`");
		} else if (userID == 1) {
			StringBuilder message = new StringBuilder();
			for (long id : store.getAllBlogPosts().keySet()) {
				if (!store.getBlogPosts(id).isEmpty()) {
					message.append(String.format("Blog posts assigned to %s:%n", formatMention(id)));
					List<BlogPost> posts = store.getBlogPosts(id);
					for (int postID = 0; postID < posts.size(); postID++)
						message.append(String.format("%d. %s%n", postID + 1, posts.get(postID)));
				} else {
					message.append(String.format("No blog posts assigned to %s%n", formatMention(id)));
				}
				message.append("\n");
			}
			if (message.length() > 0) {
				message.append("\n\nIronReignBot made by Arjun Vikram");
				replyTo(msg, message.toString());
			}
			else
				replyTo(msg, "Nobody has any blog posts yet.");
		} else if (store.getAllBlogPosts().containsKey(userID) && !store.getBlogPosts(userID).isEmpty()) {
			StringBuilder message = new StringBuilder();
			message.append(String.format("Blog posts assigned to %s:%n", formatMention(userID)));
			List<BlogPost> posts = store.getBlogPosts(userID);
			for (int postID = 0; postID < posts.size(); postID++)
				message.append(String.format("%d. %s%n", postID + 1, posts.get(postID)));
			replyTo(msg, message.toString());
		} else {
			replyTo(msg, "No blog posts assigned to %s", formatMention(userID));
		}
	}

}
