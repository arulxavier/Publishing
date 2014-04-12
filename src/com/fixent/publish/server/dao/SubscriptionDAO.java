package com.fixent.publish.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SubscriptionInfo;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class SubscriptionDAO 
extends BaseDAO {
	


	@SuppressWarnings("unchecked")
	public List<Subscription> getSubscriptions(SubscriptionInfo subscriptionInfo) {
		
		List<Subscription> subscriptions;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Subscription.class);
		
		if (subscriptionInfo != null) {
			
			if (subscriptionInfo.getFromDate() != null && subscriptionInfo.getToDate() != null) {
				criteria.add(Restrictions.between("subscriptionExpiredDate",
						subscriptionInfo.getFromDate(), subscriptionInfo.getToDate()));
			}
			Criteria bookCriteria = criteria
					.createCriteria("book");
			if (subscriptionInfo.getBookName() != null) {
				bookCriteria.add(Restrictions.ilike("name", "%"
						+ subscriptionInfo.getBookName().trim() + "%"));
			}
			if (subscriptionInfo.getSubscriptionCode() != null && subscriptionInfo.getSubscriptionCode().length() > 0) {
				criteria.add(Restrictions.ilike("subscriptionCode", "%"
						+ subscriptionInfo.getSubscriptionCode().trim() + "%"));
			}
		
		}
		
		subscriptions = criteria.list();
		initializeSubscriptions(subscriptions);
		return subscriptions;
	}

	public Subscription getSubscription(SubscriptionInfo subscriptionInfo) {
		
		Subscription subscription;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Subscription.class);
		
		if (subscriptionInfo != null) {
			
			if (subscriptionInfo.getSubscriptionCode() != null) {
				criteria.add(Restrictions.eq("subscriptionCode", subscriptionInfo.getSubscriptionCode()));
			}
		}
		subscription = (Subscription) criteria.list().get(0);
		initializeSubscription(subscription);
		return subscription;
	}

	private void initializeSubscriptions(List<Subscription> subscriptions) {
		
		for (Subscription subscription : subscriptions) {
			
			initializeSubscription(subscription);
		}
		
	}

	private void initializeSubscription(Subscription subscription) {
		
		if (subscription instanceof HibernateProxy) {
			
			HibernateProxy hibernateProxy = (HibernateProxy) subscription;
			Subscription subscriptionLocal = (Subscription) hibernateProxy;
			Hibernate.initialize(subscriptionLocal);
		} else {
			Hibernate.initialize(subscription);
		}
		
		initializeBook(subscription.getBook());
		initializeSubsciber(subscription.getSubscriber());
	}

	private void initializeSubsciber(Subscriber subscriber) {
		
		if (subscriber instanceof HibernateProxy) {
			
			HibernateProxy hibernateProxy = (HibernateProxy) subscriber;
			Subscriber subscriberLocal = (Subscriber) hibernateProxy;
			Hibernate.initialize(subscriberLocal);
		} else {
			Hibernate.initialize(subscriber);
		}
	}

	private void initializeBook(Book book) {
		
		if (book instanceof HibernateProxy) {
			
			HibernateProxy hibernateProxy = (HibernateProxy) book;
			Book bookLocal = (Book) hibernateProxy;
			Hibernate.initialize(bookLocal);
		} else {
			Hibernate.initialize(book);
		}
	}

	public Integer getSubscriptionNumber(SubscriptionInfo subscriptionInfo) 
	throws Exception {
		
		Integer subscriptionNumber = 0;
		Session session = getSession();
		connection = session.connection();

		StringBuffer query = new StringBuffer();
		query.append(" SELECT MAX(SB.SUBSCRIPTION_NUMBER) \n");
		query.append(" FROM SUBSCRIPTION SB, BOOK BK \n");
		query.append(" WHERE SB.BOOK_ID = BK.ID \n");
		query.append(" AND SB.SUBSCRIPTION_GROUP = ? \n");
		query.append(" AND BK.NAME = ? \n");
		query.append(" \n");
		
		preparedStatement = connection.prepareStatement(query.toString());
		preparedStatement.setString(1, subscriptionInfo.getSubscriptionGroup());
		preparedStatement.setString(2, subscriptionInfo.getBookName());
		
		resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			subscriptionNumber = resultSet.getInt(1);			
		}
		 
		return subscriptionNumber;
	}

}
