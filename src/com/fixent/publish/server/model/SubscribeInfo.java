package com.fixent.publish.server.model;

import java.util.Date;
import java.util.Set;

public class SubscribeInfo {
	
	int id;
	Book book;
	Subscriber subscriber;
	Date startDate;
	Date endDate;
	Set<DeliverySchedule> deliverySchedules;
}
