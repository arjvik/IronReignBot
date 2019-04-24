package com.arjvik.robotics.ironreignbot.handlers.misc;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;
import com.arjvik.robotics.ironreignbot.handlers.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

@EventHandler
@Disabled
public class PingHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
			  .on(MessageCreateEvent.class)
			  .map(MessageCreateEvent::getMessage)
			  .filter(msg -> msg.getContent()
					  			.map("!ping"::equals)
					  			.orElse(false))
			  .flatMap(Message::getChannel)
			  .flatMap(channel -> channel.createMessage("no u"))
			  .subscribe();
	}

}
