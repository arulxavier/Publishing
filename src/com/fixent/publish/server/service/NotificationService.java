package com.fixent.publish.server.service;

import java.util.List;

import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.info.SearchInfo;

public interface NotificationService {
	
	List<SubscribeInfo> searchSubscriberInfo(SearchInfo searchInfo);

}
