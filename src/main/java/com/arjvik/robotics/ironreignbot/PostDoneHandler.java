package com.arjvik.robotics.ironreignbot;

import com.arjvik.robotics.ironreignbot.handlers.AbstractHandler;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class PostDoneHandler extends AbstractHandler {

	@Override
	public void setupRoute(DiscordClient client) {
		client.getEventDispatcher()
		  .on(MessageCreateEvent.class)
		  .subscribe(e -> {
				Message msg = e.getMessage();
				if (!msg.getContent().get().startsWith("!blog done "))
					return;
				String[] cmd = msg.getContent().map(s -> s.substring("!blog done ".length())).map((s -> s.split("\\s+", 2))).get();
		  });
	}

}
