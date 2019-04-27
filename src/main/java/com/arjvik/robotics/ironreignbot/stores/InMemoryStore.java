package com.arjvik.robotics.ironreignbot.stores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class InMemoryStore implements BlogPostStore {

	private final ObjectMapper mapper = new ObjectMapper() {{
		registerModule(new JavaTimeModule());
		disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}};
	
	Map<Long, List<BlogPost>> posts;
	
	public InMemoryStore() {
		posts = new ConcurrentHashMap<>();
	}
	
	public InMemoryStore(Map<Long, List<BlogPost>> newPosts) {
		this();
		posts.putAll(newPosts);
	}
	
	@Override
	public void assignBlogPost(long userID, BlogPost post) {
		if (posts.containsKey(userID)) {
			posts.get(userID).add(post);
		} else {
			ArrayList<BlogPost> list = new ArrayList<>();
			list.add(post);
			posts.put(userID, list);
		}

	}

	@Override
	public List<BlogPost> getBlogPosts(long userID) {
		if (!posts.containsKey(userID))
			return Collections.emptyList();
		else
			return Collections.unmodifiableList(posts.get(userID));
	}

	@Override
	public Map<Long, List<BlogPost>> getAllBlogPosts() {
		return Collections.unmodifiableMap(posts);
	}
	
	@Override
	public boolean removeBlogPost(long userID, int id) {
		if (!posts.containsKey(userID) || posts.get(userID).size() < id)
			return false;
		posts.get(userID).remove(id-1);
		return true;
	}
	
	@Override
	public void removeAllPosts() {
		posts.clear();
	}

	@Override
	public String exportStore() {
		try {
			return mapper.writeValueAsString(posts);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean importStore(String input) {
		try {
			Map<Long, List<BlogPost>> newPosts = mapper.readValue(input, new TypeReference<Map<Long, List<BlogPost>>>(){});
			posts.putAll(newPosts);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
