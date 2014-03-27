package com.fixent.publish.server.model;

import java.util.Date;
import java.util.Set;

public class Book {
	
	int id;
	String name;
	String author;	
	Date publishingDate;
	String frequency;
	Set<Edition> editions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public Set<Edition> getEditions() {
		return editions;
	}
	public void setEditions(Set<Edition> editions) {
		this.editions = editions;
	}
}
