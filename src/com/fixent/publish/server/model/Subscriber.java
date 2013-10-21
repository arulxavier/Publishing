package com.fixent.publish.server.model;

import java.util.Set;

public class Subscriber {
	
	int id;
	String name;
	String mobileNumber;
	Address address;
	Set<SubscribeInfo> subscribeInfos;
}
