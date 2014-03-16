package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.SubscriberDAO;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.SubscribeService;

public class SubscribeServiceImpl implements SubscribeService {

	@Override
	public boolean createSubscriber(Subscriber subscriber) {

		boolean status = false;
		try {

			SubscriberDAO dao = new SubscriberDAO();
			int id = dao.getMaxId();
			subscriber.setId(id + 1);
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
	public List<Subscriber> getSubscriber(Integer id, String name) {

		List<Subscriber> subscribers = null;

		try {

			SubscriberDAO dao = new SubscriberDAO();
			subscribers = dao.getSubscribers(id, name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribers;
	}

	public boolean checkDuplicate(String bookName, Integer bookId,
			boolean isCreate) {
		SubscriberDAO dao = new SubscriberDAO();
		int count = dao.getsubscriberCount(bookName, bookId, isCreate);
		if (count > 0) {
			return true;
		}
		return false;
	}

	public List<SubscribeInfo> getExpiredSubscribers() {
		List<SubscribeInfo> subscribers = null;

		try {

			SubscriberDAO dao = new SubscriberDAO();
			subscribers = dao.getExpiredSubscriber();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribers;

	}
	
	public List<SubscribeInfo> getDeliverySubscribers() {
		List<SubscribeInfo> subscribers = null;

		try {

			SubscriberDAO dao = new SubscriberDAO();
			subscribers = dao.getDeliverySubscribers();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribers;

	}

}
