package com.arjvik.robotics.ironreignbot.handlers;

import com.arjvik.robotics.ironreignbot.AbstractHandler;
import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.BlogPostStore;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class PostListHandler extends AbstractHandler {

	private BlogPostStore store = BlogPostStore.store;

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .subscribe(e -> {
					Message msg = e.getMessage();
					if (!msg.getContent().get().startsWith("!blog list"))
						return;
					String cmd = msg.getContent().get().substring("!blog list".length()).trim();
					String user = null;
					if (cmd.length() == 0)
						user = msg.getAuthor().get().getMention();
					else if (cmd.matches("<@\\d+>"))
						user = cmd;
					else if (cmd.equals("all"))
						user = "all";

					if (user == null) {
						replyTo(msg, "Invalid usage of `!blog list [<@user>]`");
					} else if (user.equals("all")) {
						for (String u : store.getAllBlogPosts().keySet()) {
							replyTo(msg, "Blog posts assigned to %s:", u);
							for (BlogPost post : store.getBlogPosts(u))
								replyTo(msg, "%s", post);
						}
					} else if (store.getAllBlogPosts().containsKey(user)) {
						replyTo(msg, "Blog posts assigned to %s:", user);
						for (BlogPost post : store.getBlogPosts(user))
							replyTo(msg, "%s", post);
					} else {
						replyTo(msg, "No blog posts assigned to %s", user);
					}
			  });
	}

}
