package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;

public interface Handler {
	public void setCommandPrefix(String prefix);
	public void setupRoute(DiscordClient client);
}
