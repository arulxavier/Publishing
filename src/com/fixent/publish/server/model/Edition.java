package com.fixent.publish.server.model;

import java.util.Date;

public class Edition {
	
	int id;
	String name;
	Date editionDate;
	Book book;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getEditionDate() {
		return editionDate;
	}
	public void setEditionDate(Date editionDate) {
		this.editionDate = editionDate;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
