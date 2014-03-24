package com.fixent.publish.server.service.impl;

import java.util.Date;
import java.util.List;

import com.fixent.publish.server.dao.DeliveryDAO;
import com.fixent.publish.server.model.DeliverySchedule;
import com.fixent.publish.server.service.DeliveryService;

public class DeliveryServiceImpl 
implements DeliveryService {

	
	public List<DeliverySchedule> getDeliverySchedules(Date date) {
		
		List<DeliverySchedule> deliverySchedules = null;
		
		try {
			
			DeliveryDAO dao = new DeliveryDAO();
			deliverySchedules = dao.getDeliverySchedules(date);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deliverySchedules;
	}

}
