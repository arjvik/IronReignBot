package com.arjvik.robotics.ironreignbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.EventHandler;
import com.arjvik.robotics.ironreignbot.handlers.Handler;
import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class App {
	
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException, ReflectiveOperationException {

		Properties auth = new Properties();

		auth.load(App.class.getResourceAsStream("/auth.properties"));

		String token = auth.getProperty("token");
		
		AbstractAdminHandler.ADMIN_USER_ID = Long.parseLong(auth.getProperty("admin"));

		final DiscordClient client = new DiscordClientBuilder(token).build();
		for (Class<? extends Handler> c : getHandlers()) {
			Handler handler = c.getConstructor().newInstance();
			handler.setupRoute(client);
		}

		client.login().block();

	}
	
	
	private static List<Class<? extends Handler>> getHandlers() {
		String pkg = Handler.class.getPackage().getName();
		String handlerAnnotation = EventHandler.class.getName();
		String disabledAnnotation = Disabled.class.getName();
		
		List<Class<? extends Handler>> handlers = new ArrayList<>();
		try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages(pkg).scan()) {
			for (ClassInfo handlerClassInfo : scanResult.getClassesWithAnnotation(handlerAnnotation))
				if (!handlerClassInfo.hasAnnotation(disabledAnnotation))
					handlers.add(handlerClassInfo.loadClass(Handler.class));
		}
		
		log.info("Found handlers: {}", handlers.stream().map(Class::getSimpleName).collect(Collectors.toList()));
		
		return handlers;
	}

}
