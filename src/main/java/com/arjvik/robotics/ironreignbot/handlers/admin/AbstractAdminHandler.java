package com.arjvik.robotics.ironreignbot.handlers.admin;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

public abstract class AbstractAdminHandler extends AbstractHandler {

	public static String ADMIN_USER;
	
	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage().getAuthor().map(User::getMention).map(ADMIN_USER::equals).orElse(false))
			  .subscribe(e -> onMessageEvent(e.getMessage(), e.getMessage().getContent().orElse(null), client));
	}
	
	protected abstract void onMessageEvent(Message msg, String content, DiscordClient client);

}
