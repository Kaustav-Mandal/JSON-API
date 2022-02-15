package com.json.api.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Post 
{
	int id;
	String author;
	int authorId;
	double likes;
	float popularity;
	double reads;
	ArrayList<String> tags;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public double getLikes() {
		return likes;
	}
	public void setLikes(double likes) {
		this.likes = likes;
	}
	public float getPopularity() {
		return popularity;
	}
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	public double getReads() {
		return reads;
	}
	public void setReads(double reads) {
		this.reads = reads;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
		
	
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", author=" + author + ", authorId=" + authorId + ", likes=" + likes + ", popularity="
				+ popularity + ", reads=" + reads + ", tags=" + tags + "]";
	}
	
	
}
