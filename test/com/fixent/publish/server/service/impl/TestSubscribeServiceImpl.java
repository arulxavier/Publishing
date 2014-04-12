package com.fixent.publish.server.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.Subscriber;

public class TestSubscribeServiceImpl {
	
	static void createSubscribe() {
		
		Subscriber subscriber = new Subscriber();
		
//		subscriber.setId(1);
		subscriber.setName("Name");
		subscriber.setMobileNumber("4561237890");
		
		/*subscriber.setStreet("Street");
		subscriber.setCity("City");
		subscriber.setState("State");
		subscriber.setCountry("Country");*/
		
		Set<Subscription> subscribeInfos = new HashSet<Subscription>();
		Subscription subscription = new Subscription();
//		subscribeInfo.setNoOfYear(1);
//		subscribeInfo.setSubscribeDate(new Date());
//		subscribeInfo.setExpiredDate(new Date());
		Book book = new Book();
		book.setId(0);
		subscription.setBook(book);
		subscribeInfos.add(subscription);
		subscription.setSubscriber(subscriber);
		
		subscriber.setSubscriptions(subscribeInfos);
		
		SubscribeServiceImpl impl = new SubscribeServiceImpl();
		impl.createSubscriber(subscriber);
	}
	
	public static void main(String[] args) {
		createSubscribe();
	}

}
