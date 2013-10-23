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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Set<DeliverySchedule> getDeliverySchedules() {
		return deliverySchedules;
	}
	public void setDeliverySchedules(Set<DeliverySchedule> deliverySchedules) {
		this.deliverySchedules = deliverySchedules;
	}
	
	
}
