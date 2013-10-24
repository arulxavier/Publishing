package com.fixent.publish.server.model;

import java.util.Date;
import java.util.Set;

public class SubscribeInfo {
	
	int id;
	int noOfYear;
	Date startDate;
	Date endDate;
	Book book;
	Subscriber subscriber;
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
	public int getNoOfYear() {
		return noOfYear;
	}
	public void setNoOfYear(int noOfYear) {
		this.noOfYear = noOfYear;
	}
	
	
}
