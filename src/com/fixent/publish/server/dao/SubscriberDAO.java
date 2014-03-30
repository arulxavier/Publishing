package com.fixent.publish.server.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Edition;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;

public class SubscriberDAO extends BaseDAO {

	@SuppressWarnings("deprecation")
	public int getMaxId() throws Exception {

		int id = 0;

		Session session = getSession();
		Connection connection = session.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select max(id) from SUBSCRIBER;");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		session.close();

		return id;
	}
	
	public int getAddressId() throws Exception {

		int id = 0;

		Session session = getSession();
		Connection connection = session.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select max(id) from ADDRESS;");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		session.close();

		return id;
	}

	public boolean createSubscriber(Subscriber subscriber) {

		Session session = getSession();
		session.beginTransaction();
		session.save(subscriber);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	public boolean createAddress(Address address) {

		Session session = getSession();
		session.beginTransaction();
		session.save(address);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean modifySubscriber(Subscriber subscriber) {

		Session session = getSession();
		session.beginTransaction();
		session.merge(subscriber);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean deleteSubscriber(Subscriber subscriber) {

		Session session = getSession();
		session.beginTransaction();
		session.delete(subscriber);
		session.getTransaction().commit();
		session.close();
		return false;
	}

	public Subscriber getSubscriber(String subscriberName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Subscriber> getSubscribers(Integer id, String name, int pincode) {

		Session session = getSession();
		Criteria criteria = session.createCriteria(Subscriber.class);
		if (id != null && id > 0) {			
			criteria.add(Restrictions.eq("id", id));
		}
		if (name != null && !name.isEmpty()) {			
			criteria.add(Restrictions.ilike("name", "%"+name+"%"));
		}
		Criteria addressCriteria = criteria.createCriteria("address");
		if (pincode != 0) {
			addressCriteria.add(Restrictions.eq("pincode", pincode));
		}
		List<Subscriber> students = criteria.list();
		initializeSubscriber(students);
		session.close();
		return students;
	}

	private void initializeSubscriber(List<Subscriber> subscribers) {

		for (Subscriber subscriber : subscribers) {

			Hibernate.initialize(subscriber);
			Hibernate.initialize(subscriber.getSubscribeInfos());
			Hibernate.initialize(subscriber.getAddress());
			

			for (SubscribeInfo subscribeInfo : subscriber.getSubscribeInfos()) {
				Hibernate.initialize(subscribeInfo);
				Hibernate.initialize(subscribeInfo.getBook());				
			}
		}
	}
	
	private void initializeSubscriberModel(Subscriber subscriber) {
		
		
		if (subscriber instanceof HibernateProxy) {
			HibernateProxy hibernateProxy = (HibernateProxy) subscriber;
			Subscriber subscriber2 = (Subscriber) hibernateProxy;
			Hibernate.initialize(subscriber2);
		}
	}

	private void initializeSubscriberInfo(List<SubscribeInfo> subscribeInfos) {

		if (subscribeInfos != null && !subscribeInfos.isEmpty()) {
			for (SubscribeInfo subscribeInfo : subscribeInfos) {
				Hibernate.initialize(subscribeInfo);
				Hibernate.initialize(subscribeInfo.getBook());
				Hibernate.initialize(subscribeInfo.getBook());
//				Hibernate.initialize(subscribeInfo.getSubscriber());
				this.initializeSubscriberModel(subscribeInfo.getSubscriber());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public int getSubscriberForBook(int bookId) {

		Session session = getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(SubscribeInfo.class);
		criteria.createCriteria("book");
		criteria.add(Restrictions.like("book.id", bookId));
		List<SubscribeInfo> subscribers = criteria.list();
		session.getTransaction().commit();
		session.close();
		return subscribers != null ? subscribers.size() : 0;
	}

	public int getsubscriberCount(String bookName, Integer bookId,
			boolean isCreate) {
		BigInteger count;
		Session session = getSession();
		StringBuilder builder = new StringBuilder();
		builder.append("select count(bk.id) from SUBSCRIBER bk where bk.name='");
		builder.append(bookName + "'");
		if (!isCreate) {
			builder.append(" and bk.id !=");
			builder.append(bookId);
		}
		Query query = session.createSQLQuery(builder.toString());
		count = (BigInteger) query.uniqueResult();
		return count.intValue();
	}

	public List<SubscribeInfo> getExpiredSubscriber() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(SubscribeInfo.class);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		criteria.add(Restrictions.le("expiredDate", calendar.getTime()));
		List<SubscribeInfo> subscriberinfos = criteria.list();
		initializeSubscriberInfo(subscriberinfos);
		return subscriberinfos;
	}

	public List<SubscribeInfo> getDeliverySubscribers() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(SubscribeInfo.class);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		/*calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));*/
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		

		/*calendar2.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));*/
		criteria.add(
				Restrictions.ge("expiredDate", calendar.getTime()));
		List<SubscribeInfo> subscriberinfos = criteria.list();
		initializeSubscriberInfo(subscriberinfos);
		return subscriberinfos;
	}
	
	public List<SubscribeInfo> getSubscribersByBook(Book book) {
		
		
		Session session = getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(SubscribeInfo.class);
		criteria.createCriteria("book");
		criteria.add(Restrictions.like("book.id", book.getId()));
		List<SubscribeInfo> subscribeInfos = criteria.list();
		session.getTransaction().commit();
//		session.close();
		initializeSubscribeInfo(subscribeInfos);
		return subscribeInfos;
	}
	
	private void initializeSubscribeInfo(List<SubscribeInfo> subscribeInfos) {
		
		for (SubscribeInfo subscribeInfo : subscribeInfos) {
			
			if (subscribeInfo instanceof HibernateProxy) {
				HibernateProxy hibernateProxy = (HibernateProxy) subscribeInfo;
				SubscribeInfo subscribeInfo2 = (SubscribeInfo) hibernateProxy;
				Hibernate.initialize(subscribeInfo2);
				
				Book book = subscribeInfo.getBook();
				if (book instanceof HibernateProxy) {
					
					HibernateProxy hibernateProxy2 = (HibernateProxy) book;
					Book book2 = (Book) hibernateProxy2;
					Hibernate.initialize(book2);
					 
				} else {
					Hibernate.initialize(book);
				}
				
				Subscriber subscriber = subscribeInfo.getSubscriber();
				if (subscriber instanceof HibernateProxy) {
					
					HibernateProxy hibernateProxy2 = (HibernateProxy) subscriber;
					Subscriber subscriber2 = (Subscriber) hibernateProxy2;
					Hibernate.initialize(subscriber2);
					 
				} else {
					Hibernate.initialize(subscriber);
				}
				
			} else {
				
				Book book = subscribeInfo.getBook();
				if (book instanceof HibernateProxy) {
					
					HibernateProxy hibernateProxy2 = (HibernateProxy) book;
					Book book2 = (Book) hibernateProxy2;
					Hibernate.initialize(book2);
					 
				} else {
					Hibernate.initialize(book);
				}
				
				Subscriber subscriber = subscribeInfo.getSubscriber();
				if (subscriber instanceof HibernateProxy) {
					
					HibernateProxy hibernateProxy2 = (HibernateProxy) subscriber;
					Subscriber subscriber2 = (Subscriber) hibernateProxy2;
					Hibernate.initialize(subscriber2);
					 
				} else {
					Hibernate.initialize(subscriber);
				}
				
				
			}
			
		}
	}

	public int getEditionMaxId() 
	throws Exception {

		int id = 0;

		Session session = getSession();
		Connection connection = session.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select max(id) from EDITION;");
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		session.close();

		return id;
	}

	public boolean createEdition(Edition edition) {
		
		Session session = getSession();
		session.beginTransaction();
		session.save(edition);
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
