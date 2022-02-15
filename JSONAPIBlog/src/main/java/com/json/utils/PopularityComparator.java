package com.json.utils;

import java.util.Comparator;

import com.json.api.entities.Post;

public class PopularityComparator implements Comparator<Post>{

	@Override
	public int compare(Post o1, Post o2) 
	{
		if(o1.getPopularity() == o2.getPopularity())  
			return 0;  
			else if(o1.getPopularity() < o2.getPopularity())  
			return 1;  
			else  
			return -1;  
	}

}
