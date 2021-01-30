package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.GatewayDiscordClient;

public interface Handler {
	public void setCommandPrefix(String prefix);
	public void setupRoute(GatewayDiscordClient client);
}
