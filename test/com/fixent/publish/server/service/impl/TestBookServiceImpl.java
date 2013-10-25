package com.fixent.publish.server.service.impl;

import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Frequency;

public class TestBookServiceImpl {
	
	static void createBook() {
		
		Book book = new Book();
		book.setName("Sample Book");
		book.setFrequency(Frequency.MONTHLY.getStatusCode());
		
		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		bookServiceImpl.createBook(book);
	}
	
	public static void main(String[] args) {
		
		createBook();
	}

}
