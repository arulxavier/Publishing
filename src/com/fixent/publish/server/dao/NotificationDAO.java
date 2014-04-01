package com.fixent.publish.server.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.info.SearchInfo;

public class NotificationDAO 
extends BaseDAO {

	public List<SubscribeInfo> searchSubscriberInfo(SearchInfo info)
	throws Exception {

		Session session = getSession();
		Criteria criteria = session.createCriteria(SubscribeInfo.class);
		
		if (info != null) {
			
			if (info.getFromDate() != null) {
				criteria.add(Restrictions.ge("expiredDate", info.getFromDate()));
			}
			if (info.getToDate() != null) {
				criteria.add(Restrictions.le("expiredDate", info.getToDate()));
			}
		}
		List<SubscribeInfo> subscriberinfos = criteria.list();
		initializeSubscribeInfo(subscriberinfos);
		return subscriberinfos;
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

}
