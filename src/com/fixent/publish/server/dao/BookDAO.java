package com.fixent.publish.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;

public class BookDAO 
extends BaseDAO {

	public boolean createBook(Book book) {
		
		Session session = getSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return false;
	}

	public boolean modifyBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	public Book getBook(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getBooks() {
		
		Session session = getSession();
		Criteria criteria = session.createCriteria(Book.class);
		return criteria.list();
	}

}
