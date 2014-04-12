package com.fixent.publish.server.service;

import java.util.List;

import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SearchInfo;

public interface NotificationService {
	
	List<Subscription> searchSubscriberInfo(SearchInfo searchInfo);

}
