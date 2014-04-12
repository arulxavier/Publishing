package com.fixent.publish.server.service;

import java.util.List;

import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SubscriptionInfo;

public interface SubscriptionService {
	
	public List<Subscription> getSubscriptions(SubscriptionInfo subscriptionInfo);
	
	public Subscription getSubscription(SubscriptionInfo subscriptionInfo);
	
	public String getSubscriptionCode(SubscriptionInfo subscriptionInfo);

}
