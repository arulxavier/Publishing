package com.fixent.publish.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;

import com.fixent.publish.server.common.BaseDAO;
import com.fixent.publish.server.model.Subscriber;

public class SubscriberDAO 
extends BaseDAO {
	
	@SuppressWarnings("deprecation")
	public int getMaxId() 
			throws Exception {

		int id = 0;

		Session session = getSession();
		Connection connection = session.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select max(id) from SUBSCRIBER;");
		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			id = resultSet.getInt(1);
		}

		return id;
	}

	public boolean createSubscriber(Subscriber subscriber) {
		
		Session session = getSession();
		session.beginTransaction();
		session.save(subscriber);
		session.getTransaction().commit();
		return true;
	}

	public boolean modifySubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		return false;
	}

	public Subscriber getSubscriber(String subscriberName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Subscriber> getSubscribers() {
		// TODO Auto-generated method stub
		return null;
	}

}
