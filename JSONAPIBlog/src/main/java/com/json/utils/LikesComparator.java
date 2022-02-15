package com.json.utils;

import java.util.Comparator;

import com.json.api.entities.Post;

public class LikesComparator implements Comparator<Post>
{
	@Override
	public int compare(Post o1, Post o2) 
	{
		if(o1.getLikes() == o2.getLikes())  
			return 0;  
			else if(o1.getLikes() < o2.getLikes())  
			return 1;  
			else  
			return -1;  
	}
}
