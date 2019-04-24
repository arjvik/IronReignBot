package com.arjvik.robotics.ironreignbot.handlers.misc;

import java.util.Optional;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;
import com.arjvik.robotics.ironreignbot.handlers.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

@EventHandler
@Disabled
public class LoggingHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .map(MessageCreateEvent::getMessage)
			  .map(Message::getContent)
			  .filter(Optional::isPresent)
			  .map(Optional::get)
			  .filter(s -> s.startsWith("!"))
			  .log()
			  .subscribe();
	}

}
