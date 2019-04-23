package com.arjvik.robotics.ironreignbot.handlers.blogposts;

import static com.arjvik.robotics.ironreignbot.stores.BlogPostStore.store;

import com.arjvik.robotics.ironreignbot.handlers.AbstractImperativeHandler;

import discord4j.core.object.entity.Message;

public class PostDoneHandler extends AbstractImperativeHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (!content.startsWith("!blog done"))
			return;
		String[] cmd = content.substring("!blog done".length()).trim().split("\\s+", 2);
		if (cmd.length == 1 && cmd[0].matches("\\d+")) {
			String user = msg.getAuthor().get().getMention();
			int id = Integer.parseInt(cmd[0]);
			if (store.removeBlogPost(user, id)) {
				replyTo(msg, "Sucessfully removed %s's blog post (send `!blog list` again for new IDs)", user);
			} else {
				replyTo(msg, "Unable to remove post #%d from %s", id, user);
			}
		} else if (cmd.length == 2 && cmd[0].matches("<!?@\\d+>") && cmd[1].matches("\\d+")) {
			String user = cmd[0];
			int id = Integer.parseInt(cmd[1]);
			if (store.removeBlogPost(user, id)) {
				replyTo(msg, "Sucessfully removed %s's blog post (send `!blog list` again for new IDs)", user);
			} else {
				replyTo(msg, "Unable to remove post #%d from %s", id, user);
			}
		} else {
			replyTo(msg, "Invalid usage of `!blog done [<@user>] <post id>");
		}
	}

}
