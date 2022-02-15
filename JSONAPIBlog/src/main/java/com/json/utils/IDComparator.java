package com.json.utils;

import java.util.Comparator;

import com.json.api.entities.Post;

public class IDComparator implements Comparator<Post> 
{

	@Override
	public int compare(Post o1, Post o2) 
	{
		if(o1.getId() == o2.getId())  
			return 0;  
			else if(o1.getId() < o2.getId())  
			return 1;  
			else  
			return -1;  
	}

}
