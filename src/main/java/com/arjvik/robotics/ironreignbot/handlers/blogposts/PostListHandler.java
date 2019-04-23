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
		String user = null;
		if (cmd.length() == 0)
			user = msg.getAuthor().get().getMention().replaceAll("<@!(\\d+)>", "<@\\1>");
		else if (cmd.matches("<@!?\\d+>"))
			user = cmd.replaceAll("<@!(\\d+)>", "<@\\1>");
		else if (cmd.equals("all"))
			user = "all";
		if (user == null) {
			replyTo(msg, "Invalid usage of `!blog list [<@user> | all]`");
		} else if (user.equals("all")) {
			for (String u : store.getAllBlogPosts().keySet()) {
				if (!store.getBlogPosts(u).isEmpty()) {
					replyTo(msg, "Blog posts assigned to %s:", u);
					List<BlogPost> posts = store.getBlogPosts(u);
					for (int id = 0; id < posts.size(); id++)
						replyTo(msg, "%d. %s", id + 1, posts.get(id));
				} else {
					replyTo(msg, "No blog posts assigned to %s", u);
				}
			}
		} else if (store.getAllBlogPosts().containsKey(user) && !store.getBlogPosts(user).isEmpty()) {
			replyTo(msg, "Blog posts assigned to %s:", user);
			List<BlogPost> posts = store.getBlogPosts(user);
			for (int id = 0; id < posts.size(); id++)
				replyTo(msg, "%d. %s", id + 1, posts.get(id));
		} else {
			replyTo(msg, "(ben w/h) No blog posts assigned to %s", user);
		}
	}

}
