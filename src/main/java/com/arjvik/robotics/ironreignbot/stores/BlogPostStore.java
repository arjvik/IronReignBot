package com.arjvik.robotics.ironreignbot.stores;

import java.util.List;
import java.util.Map;

import com.arjvik.robotics.ironreignbot.BlogPost;

public interface BlogPostStore {
	
	public static final BlogPostStore store = new InMemoryStore();
	
	public void assignBlogPost(long userID, BlogPost post);
	
	public List<BlogPost> getBlogPosts(long userID);
	
	public Map<Long, List<BlogPost>> getAllBlogPosts();
	
	public boolean removeBlogPost(long userID, int id);
	
	public void removeAllPosts();
	
	public String exportStore();
	
	public boolean importStore(String input);
	
}
