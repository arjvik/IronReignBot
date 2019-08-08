package com.arjvik.robotics.ironreignbot.stores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.arjvik.robotics.ironreignbot.BlogPost;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersistentJsonStore implements BlogPostStore {

	private final ObjectMapper mapper = new ObjectMapper();

	File storage;
	
	public PersistentJsonStore() {
		storage = new File("posts.json");
		if (!storage.exists())
			removeAllPosts(); //reset file
	}
	
	private Map<Long, List<BlogPost>> readFromDisk() {
		try {
			return new HashMap<>(mapper.readValue(storage, new TypeReference<Map<Long, List<BlogPost>>>(){}));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void writeToDisk(Map<Long, List<BlogPost>> posts) {
		try {
			mapper.writeValue(storage, posts);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void assignBlogPost(long userID, BlogPost post) {
		Map<Long, List<BlogPost>> posts = readFromDisk();
		if (posts.containsKey(userID)) {
			posts.get(userID).add(post);
		} else {
			ArrayList<BlogPost> list = new ArrayList<>();
			list.add(post);
			posts.put(userID, list);
		}
		writeToDisk(posts);
	}

	@Override
	public List<BlogPost> getBlogPosts(long userID) {
		Map<Long, List<BlogPost>> posts = readFromDisk();
		if (!posts.containsKey(userID))
			return Collections.emptyList();
		else
			return Collections.unmodifiableList(posts.get(userID));
	}

	@Override
	public Map<Long, List<BlogPost>> getAllBlogPosts() {
		return Collections.unmodifiableMap(readFromDisk());
	}
	
	@Override
	public boolean removeBlogPost(long userID, int id) {
		Map<Long, List<BlogPost>> posts = readFromDisk();
		if (!posts.containsKey(userID) || posts.get(userID).size() < id)
			return false;
		posts.get(userID).remove(id-1);
		writeToDisk(posts);
		return true;
	}
	
	@Override
	public void removeAllPosts() {
		writeToDisk(new HashMap<>());
	}

	@Override
	public String exportStore() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(storage));
			String exported = br.lines().collect(Collectors.joining());
			br.close();
			return exported;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean importStore(String input) {
		try {
			Map<Long, List<BlogPost>> posts = readFromDisk();
			Map<Long, List<BlogPost>> newPosts = mapper.readValue(input, new TypeReference<Map<Long, List<BlogPost>>>(){});
			posts.putAll(newPosts);
			writeToDisk(posts);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
