package com.fixent.publish.server.service;

import java.util.Date;
import java.util.List;

import com.fixent.publish.server.model.DeliverySchedule;

public interface DeliveryService {
	
	List<DeliverySchedule> getDeliverySchedules(Date date);

}
