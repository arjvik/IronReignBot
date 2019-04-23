package com.arjvik.robotics.ironreignbot.handlers;

import com.arjvik.robotics.ironreignbot.handlers.admin.AdminPingHandler;
import com.arjvik.robotics.ironreignbot.handlers.admin.AdminShutdownHandler;
import com.arjvik.robotics.ironreignbot.handlers.blogposts.PostAssignHandler;
import com.arjvik.robotics.ironreignbot.handlers.blogposts.PostDoneHandler;
import com.arjvik.robotics.ironreignbot.handlers.blogposts.PostListHandler;
import com.arjvik.robotics.ironreignbot.handlers.misc.LoggingHandler;

import discord4j.core.DiscordClient;

public interface Handler {
	public void setupRoute(DiscordClient client);
	
	@SuppressWarnings("unchecked")
	public static Class<? extends Handler>[] handlers = new Class[]{
			LoggingHandler.class, PostAssignHandler.class, PostListHandler.class,
			PostDoneHandler.class, AdminShutdownHandler.class, AdminPingHandler.class
	};
}
