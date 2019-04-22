package com.arjvik.robotics.ironreignbot;

import java.io.IOException;
import java.util.Properties;

import com.arjvik.robotics.ironreignbot.handlers.Handler;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;

import static com.arjvik.robotics.ironreignbot.handlers.Handler.handlers;

public class App {

	public static void main(String[] args) throws IOException, ReflectiveOperationException {

		Properties auth = new Properties();

		auth.load(App.class.getResourceAsStream("/auth.properties"));

		String token = auth.getProperty("token");

		final DiscordClient client = new DiscordClientBuilder(token).build();

		for (Class<? extends Handler> c : handlers) {
			Handler handler = c.getConstructor().newInstance();
			handler.setupRoute(client);
		}

		client.login().block();

	}

}
