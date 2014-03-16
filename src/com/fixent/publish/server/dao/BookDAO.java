package com.fixent.publish.server.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;

public class BookDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	public int getMaxId() throws Exception {

		Integer id = 0;
		Session session = getSession();
		Query query = session.createSQLQuery("select max(id) from BOOK;");
		id = (Integer) query.uniqueResult();
		if (id == null)
		{
			id = 0;
		}
		return id;
	}

	public int getBookCount(String bookName, Integer bookId, boolean isCreate)
			{
		BigInteger count;
		Session session = getSession();
		StringBuilder builder = new StringBuilder();
		builder.append("select count(bk.id) from BOOK bk where bk.name='");
		builder.append(bookName + "'");
		if (!isCreate) {
			builder.append(" and bk.id !=");
			builder.append(bookId);
		}
		Query query = session.createSQLQuery(builder.toString());
		count = (BigInteger) query.uniqueResult();
		return count.intValue();
	}

	public boolean createBook(Book book) {

		Session session = getSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return false;
	}

	public boolean modifyBook(Book book) {

		Session session = getSession();
		session.beginTransaction();
		session.merge(book);
		session.getTransaction().commit();
		return true;
	}

	public boolean deleteBook(Book book) {

		Session session = getSession();
		session.beginTransaction();
		session.delete(book);
		session.getTransaction().commit();
		return true;
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
