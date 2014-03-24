package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.BookDAO;
import com.fixent.publish.server.dao.SubscriberDAO;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.service.BookService;

public class BookServiceImpl implements BookService {

	
	public boolean createBook(Book book) {

		boolean status = false;
		try {

			BookDAO dao = new BookDAO();
			int id = dao.getMaxId();
			book.setId(id + 1);
			status = dao.createBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
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

	
	public boolean deleteBook(Book book) {

		boolean status = false;
		try {
			BookDAO dao = new BookDAO();
			SubscriberDAO subscriberDAO = new SubscriberDAO();
			if (subscriberDAO.getSubscriberForBook(book.getId()) > 0) {
				return false;
			}
			status = dao.deleteBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
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

	public boolean checkDuplicate(String bookName, Integer bookId,
			boolean isCreate) {
		BookDAO dao = new BookDAO();
		int count = dao.getBookCount(bookName, bookId, isCreate);
		if (count > 0) {
			return true;
		}
		return false;
	}
}
