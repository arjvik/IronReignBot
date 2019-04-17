package com.arjvik.robotics.ironreignbot.handlers;

import java.time.LocalDate;
import java.util.ArrayList;

import com.arjvik.robotics.ironreignbot.AbstractHandler;
import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.BlogPostStore;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class PostAssignHandler extends AbstractHandler {

	private BlogPostStore store = BlogPostStore.store;
	
	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .subscribe(e -> {
					Message msg = e.getMessage();
					if (!msg.getContent().get().startsWith("!blog assign "))
						return;
					String[] cmd = msg.getContent().map(s -> s.substring("!blog assign ".length())).map((s -> s.split("\\s+", 2))).get();
					if (cmd.length < 2)
						replyTo(msg, "Invalid usage of `!blog assign <@user> <blog post topic>`");
					else if (!cmd[0].matches("<@\\d+>")) {
						replyTo(msg, "Invalid user: %s", cmd[0]);
					} else {
						BlogPost post = new BlogPost(cmd[1], LocalDate.now());
						store.assignBlogPost(cmd[0], post);
						replyTo(msg, "Assigned %s blog post \"%s\"", cmd[0], cmd[1]);
					}
			  });

	}

}
