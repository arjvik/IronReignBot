package com.arjvik.robotics.ironreignbot.handlers.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

@EventHandler
public class LoggingHandler extends AbstractHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher().on(MessageCreateEvent.class).map(MessageCreateEvent::getMessage)
				.filter(message -> message.getContent().isPresent())
				.filter(message -> message.getContent().get().startsWith("!")
						|| message.getGuild().block().getName().equals("ironReignBot"))
				.subscribe(message -> log.info("{} <@{}>: \"{}\"",
												message.getAuthor().get().getUsername(),
												message.getAuthor().get().getId().asLong(),
												message.getContent().get()));
	}

}