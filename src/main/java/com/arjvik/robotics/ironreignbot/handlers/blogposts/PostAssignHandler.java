package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler("!blog assign")
public class PostAssignHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		String[] cmd = content.split("\\s+", 2);
		if (cmd.length < 2 || !isMention(cmd[0]))
			replyTo(msg, "Invalid usage of `!blog assign <@user> <blog post topic>`");
		else {
			BlogPost post = new BlogPost(cmd[1], now());
			store.assignBlogPost(getIDFromMention(cmd[0]), post);
			replyTo(msg, "Assigned %s blog post \"%s\"", formatMention(cmd[0]), cmd[1]);
		}
	}

	private static String now() {
		return LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
	}

}
