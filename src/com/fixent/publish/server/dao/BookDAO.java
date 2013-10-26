package com.fixent.publish.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;

public class BookDAO 
extends BaseDAO {
	
	@SuppressWarnings("deprecation")
	public int getMaxId() 
			throws Exception {

		int id = 0;

		Session session = getSession();
		Connection connection = session.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select max(id) from BOOK;");
		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			id = resultSet.getInt(1);
		}

		return id;
	}

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
