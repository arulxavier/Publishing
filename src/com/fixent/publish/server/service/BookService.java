package com.fixent.publish.server.service;

import java.util.List;

import com.fixent.publish.server.model.Book;

public interface BookService {
	
	boolean createBook(Book book);
	
	boolean modifyBook(Book book);
	
	boolean deleteBook(Book book);
	
	Book getBook(String Name);

	Book getBookById(int id);
	
	List<Book> getBooks();
	

}
