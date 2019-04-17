package com.arjvik.robotics.ironreignbot;

import java.util.List;
import java.util.Map;

public interface BlogPostStore {
	
	public static final BlogPostStore store = new InMemoryStore();
	
	public void assignBlogPost(String user, BlogPost post);
	
	public List<BlogPost> getBlogPosts(String user);
	
	public Map<String, List<BlogPost>> getAllBlogPosts();
	
}
