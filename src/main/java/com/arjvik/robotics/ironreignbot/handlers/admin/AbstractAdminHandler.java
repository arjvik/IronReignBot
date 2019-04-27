package com.arjvik.robotics.ironreignbot.handlers.admin;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;

public abstract class AbstractAdminHandler extends AbstractHandler {

	public static long ADMIN_USER_ID;
	
	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage().getAuthor().map(User::getId).map(Snowflake::asLong).map(id -> id == ADMIN_USER_ID).orElse(false))
			  .filter(e -> e.getMessage().getContent().isPresent())
			  .subscribe(e -> onMessageEvent(e.getMessage(), e.getMessage().getContent().get(), client));
	}
	
	protected abstract void onMessageEvent(Message msg, String content, DiscordClient client);

}
