package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.SubscriberDAO;
import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Edition;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.SubscribeService;

public class SubscribeServiceImpl implements SubscribeService {

	
	public boolean createSubscriber(Subscriber subscriber) {

		boolean status = false;
		try {

			SubscriberDAO dao = new SubscriberDAO();
			
			Address address = subscriber.getAddress();
			int addressId = dao.getAddressId();
			address.setId(addressId);
			dao.createAddress(address);
			subscriber.setAddress(address);			
			int id = dao.getMaxId();
			subscriber.setId(id + 1);
			status = dao.createSubscriber(subscriber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
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
	
	public List<SubscribeInfo> getSubscribersByBook(Book book) {
		
		
		List<SubscribeInfo> subscribeInfos = null;

		try {

			SubscriberDAO dao = new SubscriberDAO();
			subscribeInfos = dao.getSubscribersByBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribeInfos;
		
	}
	
	public boolean saveEdition(Edition edition) {

		boolean status = false;
		try {

			SubscriberDAO dao = new SubscriberDAO();
			
			int id = dao.getEditionMaxId();
			edition.setId(id + 1);
			status = dao.createEdition(edition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
