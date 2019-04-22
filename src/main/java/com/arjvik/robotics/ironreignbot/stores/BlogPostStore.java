package com.arjvik.robotics.ironreignbot.stores;

import java.util.List;
import java.util.Map;

import com.arjvik.robotics.ironreignbot.BlogPost;

public interface BlogPostStore {
	
	public static final BlogPostStore store = new InMemoryStore();
	
	public void assignBlogPost(String user, BlogPost post);
	
	public List<BlogPost> getBlogPosts(String user);
	
	public Map<String, List<BlogPost>> getAllBlogPosts();
	
	public boolean removeBlogPost(String user, int id);
	
}
