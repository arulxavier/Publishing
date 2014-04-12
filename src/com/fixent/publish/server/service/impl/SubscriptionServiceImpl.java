package com.fixent.publish.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fixent.publish.server.dao.SubscriptionDAO;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SubscriptionInfo;
import com.fixent.publish.server.service.SubscriptionService;

public class SubscriptionServiceImpl 
implements SubscriptionService {

	public List<Subscription> getSubscriptions(SubscriptionInfo subscriptionInfo) {
		
		List<Subscription> subscriptions = new ArrayList<Subscription>();
		
		try {
			
			SubscriptionDAO dao = new SubscriptionDAO();
			subscriptions = dao.getSubscriptions(subscriptionInfo);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return subscriptions;
	}

	public Subscription getSubscription(SubscriptionInfo subscriptionInfo) {
		
		Subscription subscription = new Subscription();
		
		try {
			
			SubscriptionDAO dao = new SubscriptionDAO();
			subscription = dao.getSubscription(subscriptionInfo);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return subscription;
	}

	public String getSubscriptionCode(SubscriptionInfo subscriptionInfo) {
		
		String subscriptionCode = "";
		try {
			
			SubscriptionDAO dao = new SubscriptionDAO();
			Integer subscriptionNumber = dao.getSubscriptionNumber(subscriptionInfo);
			subscriptionNumber = subscriptionNumber + 1;
			subscriptionCode = subscriptionInfo.getSubscriptionGroup() + subscriptionNumber;
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return subscriptionCode;
	}

}
