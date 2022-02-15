package com.json.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.json.api.dto.SuccessResponse;
import com.json.api.entities.Post;
import com.json.api.entities.Posts;
import com.json.api.exceptionhandlers.ErrorResponse;
import com.json.utils.IDComparator;
import com.json.utils.LikesComparator;
import com.json.utils.PopularityComparator;
import com.json.utils.ReadsComparator;

@RestController
@RequestMapping("/api")
public class APIController 
{
	private static final String REQUEST_URL = "https://api.hatchways.io/assessment/blog/posts?tag=";
	@Value("${com.json.api.error.sortby}")
	private String errorSortby;
	@Value("${com.json.api.error.direction}")
	private String errorDirection;
	private ArrayList<String> validSortByValues = new ArrayList<String>(Arrays.asList("id", "reads", "likes", "popularity"));
	private ArrayList<String> validDirectionValues = new ArrayList<String>(Arrays.asList("desc", "asc"));
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/ping")
	public ResponseEntity ping()
	{
		SuccessResponse successresponse = new SuccessResponse();
		successresponse.setSuccess("true");
		return new ResponseEntity(successresponse, HttpStatus.OK);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/posts")
	public synchronized ResponseEntity get(@RequestParam(name = "tags", required=true) String tags, @RequestParam(name="sortBy", defaultValue = "id") String sortBy, @RequestParam(name="direction", defaultValue = "asc") String direction) throws UnsupportedEncodingException
	{
		Posts filteredPosts = new Posts();
		RestTemplate restTemplate = new RestTemplate();
		String[] allTags = tags.split(",");
		ArrayList<String> tagsInUrl = new ArrayList<String>();
		Collections.addAll(tagsInUrl, allTags);
		LinkedHashSet<Post> set = new LinkedHashSet<Post>();
		ArrayList<Post> postsWithTag = new ArrayList<Post>();
		
		if(!sortBy.equals(null) && !validSortByValues.contains(sortBy.trim()))
		{
			ErrorResponse errorresponse = new ErrorResponse();
			errorresponse.setError(errorSortby);
			return new ResponseEntity(errorresponse, HttpStatus.BAD_REQUEST);
		}
		else if(!direction.equals(null) && !validDirectionValues.contains(direction))
		{
			ErrorResponse errorresponse = new ErrorResponse();
			errorresponse.setError(errorDirection);
			return new ResponseEntity(errorresponse, HttpStatus.BAD_REQUEST);
		}
		
		for(String t : tagsInUrl)
		{
			String encodeStr = URLEncoder.encode(t, StandardCharsets.UTF_8.toString());
			String url = REQUEST_URL+encodeStr;
			Posts posts = restTemplate.getForObject(url, Posts.class);
			
			for(Post post : posts.getPosts())
			{
				ArrayList<String> tagsInPost = post.getTags();
				if(tagsInPost.contains(t))
					set.add(post);
			}
		}
		postsWithTag = new ArrayList<>(set);
		if(!sortBy.equals(null))
		{
			if(sortBy.equalsIgnoreCase(validSortByValues.get(0)))
				Collections.sort(postsWithTag, new IDComparator());
			else if(sortBy.equalsIgnoreCase(validSortByValues.get(1)))
				Collections.sort(postsWithTag, new ReadsComparator());
			else if(sortBy.equalsIgnoreCase(validSortByValues.get(2)))
				Collections.sort(postsWithTag, new LikesComparator());
			else if(sortBy.equalsIgnoreCase(validSortByValues.get(3)))
				Collections.sort(postsWithTag, new PopularityComparator());
		}
		if(!direction.equals(null))
		{
			if(direction.equalsIgnoreCase(validDirectionValues.get(1)))
				Collections.reverse(postsWithTag);
		}
		filteredPosts.setPosts(postsWithTag);
		return new ResponseEntity(filteredPosts, HttpStatus.OK);
		}
}
