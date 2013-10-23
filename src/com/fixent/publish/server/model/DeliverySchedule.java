package com.fixent.publish.server.model;

import java.util.Date;

public class DeliverySchedule {
	
	int id;
	SubscribeInfo subscribeInfo;
	Date expectedDelivery;
	boolean status;
	Date deliveryDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SubscribeInfo getSubscribeInfo() {
		return subscribeInfo;
	}
	public void setSubscribeInfo(SubscribeInfo subscribeInfo) {
		this.subscribeInfo = subscribeInfo;
	}
	public Date getExpectedDelivery() {
		return expectedDelivery;
	}
	public void setExpectedDelivery(Date expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	
}
