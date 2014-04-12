package com.fixent.publish.server.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.BookInfo;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class BookDAO extends BaseDAO {

	public int getMaxId() 
	throws Exception {

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

	public int getBookCount(BookInfo bookInfo) {
		
		BigInteger count;
		Session session = getSession();
		StringBuilder builder = new StringBuilder();
		builder.append("select count(bk.id) from BOOK bk where bk.name='");
		builder.append(bookInfo.getBookName() + "'");
		if (!bookInfo.getIsCreate()) {
			builder.append(" and bk.id !=");
			builder.append(bookInfo.getId());
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
		
		Session session = getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("name", name));
		Book book = (Book) criteria.list().get(0);
		initializeBook(book);
		session.getTransaction().commit();
		session.close();
		return book;
	}
	
	public void initializeBook(Book book) {
		
		if (book instanceof HibernateProxy) {
			
			HibernateProxy hibernateProxy = (HibernateProxy) book;
			book = (Book) hibernateProxy;
			Hibernate.initialize(book);
		}
	}

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {

		Session session = getSession();
		Criteria criteria = session.createCriteria(Book.class);
		return criteria.list();
	}

}
