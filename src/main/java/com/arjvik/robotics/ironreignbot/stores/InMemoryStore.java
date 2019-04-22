package com.arjvik.robotics.ironreignbot.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arjvik.robotics.ironreignbot.BlogPost;

class InMemoryStore implements BlogPostStore {

	HashMap<String, List<BlogPost>> posts;
	
	public InMemoryStore() {
		posts = new HashMap<>();
	}
	
	@Override
	public void assignBlogPost(String user, BlogPost post) {
		if (posts.containsKey(user)) {
			posts.get(user).add(post);
		} else {
			ArrayList<BlogPost> list = new ArrayList<>();
			list.add(post);
			posts.put(user, list);
		}

	}

	@Override
	public List<BlogPost> getBlogPosts(String user) {
		if (!posts.containsKey(user))
			return Collections.emptyList();
		else
			return Collections.unmodifiableList(posts.get(user));
	}

	@Override
	public Map<String, List<BlogPost>> getAllBlogPosts() {
		return Collections.unmodifiableMap(posts);
	}
	
	@Override
	public boolean removeBlogPost(String user, int id) {
		if (!posts.containsKey(user) || posts.get(user).size() < id)
			return false;
		posts.get(user).remove(id-1);
		return true;
	}

}
