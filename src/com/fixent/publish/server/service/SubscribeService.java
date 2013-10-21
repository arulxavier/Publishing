package com.fixent.publish.server.service;

import java.util.List;

import com.fixent.publish.server.model.Subscriber;

public interface SubscribeService {
	
	boolean createSubscriber(Subscriber subscriber);
	
	boolean modifySubscriber(Subscriber subscriber);
	
	boolean deleteSubscriber(Subscriber subscriber);
	
	Subscriber getSubscriber(String subscriberName);
	
	List<Subscriber> getSubscriber();

}
