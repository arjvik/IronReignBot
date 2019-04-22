package com.arjvik.robotics.ironreignbot.handlers;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.util.List;

import com.arjvik.robotics.ironreignbot.BlogPost;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class PostListHandler extends AbstractHandler {

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
							if (!store.getBlogPosts(u).isEmpty()) {
								replyTo(msg, "Blog posts assigned to %s:", u);
								List<BlogPost> posts = store.getBlogPosts(u);
								for (int id = 0; id < posts.size(); id++)
									replyTo(msg, "%d. %s", id+1, posts.get(id));
							} else {
								replyTo(msg, "No blog posts assigned to %s", u);
							}
						}
					} else if (store.getAllBlogPosts().containsKey(user) && !store.getBlogPosts(user).isEmpty()) {
						replyTo(msg, "Blog posts assigned to %s:", user);
						List<BlogPost> posts = store.getBlogPosts(user);
						for (int id = 0; id < posts.size(); id++)
							replyTo(msg, "%d. %s", id+1, posts.get(id));
					} else {
						replyTo(msg, "No blog posts assigned to %s", user);
					}
			  });
	}

}
