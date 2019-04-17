package com.arjvik.robotics.ironreignbot;

import com.arjvik.robotics.ironreignbot.handlers.LoggingHandler;
import com.arjvik.robotics.ironreignbot.handlers.PingHandler;
import com.arjvik.robotics.ironreignbot.handlers.PostAssignHandler;
import com.arjvik.robotics.ironreignbot.handlers.PostListHandler;

public final class Handlers {
	
	private Handlers() { throw new RuntimeException("Utility class"); }
	
	@SuppressWarnings("unchecked")
	public static Class<? extends Handler>[] handlers = new Class[]{LoggingHandler.class, PingHandler.class, PostAssignHandler.class, PostListHandler.class};

}
