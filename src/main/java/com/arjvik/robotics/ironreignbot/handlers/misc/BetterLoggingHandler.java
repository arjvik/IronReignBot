package com.arjvik.robotics.ironreignbot.handlers.misc;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class BetterLoggingHandler extends AbstractHandler {

	private static Logger log = Logger.getLogger(BetterLoggingHandler.class.getName());
	
	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .map(MessageCreateEvent::getMessage)
			  .filter(message -> message.getContent().get().startsWith("!") || message.getGuild().block().getName().equals("ironReignBot"))
			  .subscribe(message -> {
					log.log(Level.INFO, String.format(
						"%s <@%d> | \"%s\"",
						message.getAuthor().get().getUsername(),
						message.getAuthor().get().getId().asLong(),
						message.getContent().get()
					));
			  });
	}

}