package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.BookDAO;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.service.BookService;

public class BookServiceImpl 
implements BookService {

	@Override
	public boolean createBook(Book book) {
		
		boolean status = false;
		try {
			
			BookDAO dao = new BookDAO();
			status = dao.createBook(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean modifyBook(Book book) {
		
		boolean status = false;
		try {
			
			BookDAO dao = new BookDAO();
			status = dao.modifyBook(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteBook(Book book) {
		
		boolean status = false;
		try {
			
			BookDAO dao = new BookDAO();
			status = dao.deleteBook(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Book getBook(String name) {
		
		Book book = null;
		try {
			
			BookDAO dao = new BookDAO();
			book = dao.getBook(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book getBookById(int id) {
		
		Book book = null;
		try {
			
			BookDAO dao = new BookDAO();
			book = dao.getBookById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<Book> getBooks() {
		
		List<Book> books = null;
		try {
			
			BookDAO dao = new BookDAO();
			books = dao.getBooks();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

}
