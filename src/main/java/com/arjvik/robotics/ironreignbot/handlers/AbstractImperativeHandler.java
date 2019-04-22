package com.arjvik.robotics.ironreignbot.handlers;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public abstract class AbstractImperativeHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
		  .on(MessageCreateEvent.class)
		  .subscribe(this::onMessageEvent);
	}
	
	protected abstract void onMessageEvent(MessageCreateEvent e);

}
