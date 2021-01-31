package com.arjvik.robotics.ironreignbot.handlers.admin;

import java.util.List;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;

public abstract class AbstractAdminHandler extends AbstractMessageHandler {

	public static List<Long> ADMIN_USER_IDS;
	
	@Override
	public void setupRoute(GatewayDiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage()
					  		.getAuthor()
					  		.map(User::getId)
					  		.map(Snowflake::asLong)
					  		.map(ADMIN_USER_IDS::contains)
					  		.orElse(false))
			  .filter(e -> e.getMessage()
					  		.getContent()
					  		.startsWith(prefix))
			  .subscribe(this::dispatchMessageCreateEvent);
	}
	
}
