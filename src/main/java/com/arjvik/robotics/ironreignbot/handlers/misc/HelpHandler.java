package com.arjvik.robotics.ironreignbot.handlers.misc;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.object.entity.Message;

@EventHandler
public class HelpHandler extends AbstractMessageHandler {

	@Override
	protected void onMessageEvent(Message msg, String content) {
		if (content.equals("!help")) {
			replyTo(msg,
					  "__**IronReignBot** (created by Arjun Vikram)__\n\n"
					+ "**Commands**\n\n"
					+ "Assign Blog Posts\n"
					+ "- `!blog assign <@user> <blog post>`: Assign somebody a blogpost.\n"
					+ "- `!blog list [<@user> | all]`: List all blog posts assigned to somebody, all users, or by default yourself.\n"
					+ "- `!blog done [<@user>] <id>`: Mark a post as completed by its id.\n\n"
					+ "Manage Blog Posts\n"
					+ "- `!admin clear-posts`: Delete all blog posts.\n"
					+ "- `!admin export-posts`: Export all posts to JSON.\n"
					+ "- `!admin import-posts <JSON in triple backticks>`: Import blog posts from JSON.\n\n"
					+ "Miscelaneous\n"
					+ "- `!ping`: Respond `Pong!` to test if bot is up.\n"
					+ "- `!vote`: React with a :thumbsup: and :thumbsdown: to facilitate a vote.\n"
					+ "- `!help`: Respond with a help message.\n\n"
					+ "Admin Commands\n"
					+ "- `!admin shutdown`: Shut down the bot.");
		}
	}

}
