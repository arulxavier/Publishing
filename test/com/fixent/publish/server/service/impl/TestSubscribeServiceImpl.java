package com.fixent.publish.server.service.impl;

import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Subscriber;

public class TestSubscribeServiceImpl {
	
	void createSubscribe() {
		
		Subscriber subscriber = new Subscriber();
		
		subscriber.setName("Name");
		subscriber.setMobileNumber("4561237890");
		
		Address address = new Address();
		address.setStreet("Street");
		address.setCity("City");
		address.setState("State");
		address.setCountry("Country");
		subscriber.setAddress(address);
	}

}
