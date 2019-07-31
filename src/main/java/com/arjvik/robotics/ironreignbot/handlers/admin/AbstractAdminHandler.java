package com.arjvik.robotics.ironreignbot.handlers.admin;

import java.util.List;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;

public abstract class AbstractAdminHandler extends AbstractMessageHandler {

	public static List<Long> ADMIN_USER_IDS;
	
	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage().getAuthor().map(User::getId).map(Snowflake::asLong).map(ADMIN_USER_IDS::contains).orElse(false))
			  .filter(e -> e.getMessage().getContent().map(s -> s.startsWith(prefix)).orElse(false))
			  .subscribe(e -> onMessageEvent(e.getMessage(), e.getMessage().getContent().map(s -> s.substring(prefix.length()).trim()).orElse(null)));
	}
	
}
