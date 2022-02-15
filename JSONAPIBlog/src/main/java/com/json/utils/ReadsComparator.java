package com.json.utils;

import java.util.Comparator;

import com.json.api.entities.Post;

public class ReadsComparator implements Comparator<Post>
{

	@Override
	public int compare(Post o1, Post o2) 
	{
		if(o1.getReads() == o2.getReads())  
			return 0;  
			else if(o1.getReads() < o2.getReads())  
			return 1;  
			else  
			return -1;  
	}
}
