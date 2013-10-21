package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.SubscriberDAO;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.SubscribeService;

public class SubscribeServiceImpl 
implements SubscribeService {

	@Override
	public boolean createSubscriber(Subscriber subscriber) {
		
		boolean status = false;
		
		try {
			
			SubscriberDAO dao = new SubscriberDAO();
			status = dao.createSubscriber(subscriber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean modifySubscriber(Subscriber subscriber) {
		
		boolean status = false;
		
		try {
			
			SubscriberDAO dao = new SubscriberDAO();
			status = dao.modifySubscriber(subscriber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteSubscriber(Subscriber subscriber) {
		
		boolean status = false;
		
		try {
			
			SubscriberDAO dao = new SubscriberDAO();
			status = dao.deleteSubscriber(subscriber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Subscriber getSubscriber(String subscriberName) {
		
		Subscriber subscriber = null;
		
		try {
			
			SubscriberDAO dao = new SubscriberDAO();
			subscriber = dao.getSubscriber(subscriberName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriber;
	}

	@Override
	public List<Subscriber> getSubscriber() {
		
		List<Subscriber> subscribers = null;
		
		try {
			
			SubscriberDAO dao = new SubscriberDAO();
			subscribers = dao.getSubscribers();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribers;
	}

}
