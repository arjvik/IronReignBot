package com.arjvik.robotics.ironreignbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjvik.robotics.ironreignbot.handlers.Handler;
import com.arjvik.robotics.ironreignbot.handlers.admin.AbstractAdminHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.Disabled;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;
import com.arjvik.robotics.ironreignbot.handlers.toa.TheOrangeAllianceHandler;

import discord4j.core.DiscordClientBuilder;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class App {
	
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException {

		Properties auth = new Properties();

		auth.load(App.class.getResourceAsStream("/auth.properties"));

		String token = auth.getProperty("token");
		
		AbstractAdminHandler.ADMIN_USER_IDS = Arrays.stream(auth.getProperty("admin").split(","))
													.map(String::trim)
													.map(Long::parseLong)
													.collect(Collectors.toList());
		TheOrangeAllianceHandler.TOA_KEY = auth.getProperty("toa-key");

		DiscordClientBuilder.create(token)
							.build()
							.withGateway(client -> {
								for (Handler handler : getHandlers()) {
									handler.setupRoute(client);
								}
								return client.onDisconnect();
							})
							.block();

	}
	
	
	private static List<Handler> getHandlers() {
		String pkg = Handler.class.getPackage().getName();
		String handlerAnnotation = EventHandler.class.getName();
		String disabledAnnotation = Disabled.class.getName();
		
		List<Handler> handlers = new ArrayList<>();
		try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages(pkg).scan()) {
			for (ClassInfo handlerClassInfo : scanResult.getClassesWithAnnotation(handlerAnnotation))
				if (!handlerClassInfo.hasAnnotation(disabledAnnotation)) {
					Handler handler = handlerClassInfo.loadClass(Handler.class).newInstance();
					handler.setCommandPrefix((String) handlerClassInfo.getAnnotationInfo(handlerAnnotation).getParameterValues().getValue("value"));
					handlers.add(handler);
				}
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		
		log.info("Found handlers: {}", handlers.stream().map(Object::getClass).map(Class::getSimpleName).collect(Collectors.toList()));
		
		return handlers;
	}

}
