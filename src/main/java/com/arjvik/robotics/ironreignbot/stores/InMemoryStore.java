package com.arjvik.robotics.ironreignbot.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arjvik.robotics.ironreignbot.BlogPost;

class InMemoryStore implements BlogPostStore {

	HashMap<Long, List<BlogPost>> posts;
	
	public InMemoryStore() {
		posts = new HashMap<>();
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

}
