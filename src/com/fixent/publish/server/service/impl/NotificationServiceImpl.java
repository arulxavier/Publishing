package com.fixent.publish.server.service.impl;

import java.util.List;

import com.fixent.publish.server.dao.NotificationDAO;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.service.NotificationService;

public class NotificationServiceImpl 
implements NotificationService {

	public List<Subscription> searchSubscriberInfo(SearchInfo searchInfo) {
		
		try {
			
			NotificationDAO dao = new NotificationDAO();
			return dao.searchSubscriberInfo(searchInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
