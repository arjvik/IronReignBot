package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public abstract class AbstractMessageHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .filter(e -> e.getMessage().getContent().isPresent())
			  .subscribe(e -> onMessageEvent(e.getMessage(), e.getMessage().getContent().orElse(null)));
	}
	
	protected abstract void onMessageEvent(Message msg, String content);

}
