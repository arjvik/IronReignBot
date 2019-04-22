package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;

public interface Handler {
	public void setupRoute(DiscordClient client);
	
	@SuppressWarnings("unchecked")
	public static Class<? extends Handler>[] handlers = new Class[]{
			LoggingHandler.class, PingHandler.class, PostAssignHandler.class, PostListHandler.class, PostDoneHandler.class
	};
}
