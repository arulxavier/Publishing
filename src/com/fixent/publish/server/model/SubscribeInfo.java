package com.fixent.publish.server.model;

import java.util.Date;
import java.util.Set;

public class SubscribeInfo {
	
	Integer id;
	Integer noOfYear;
	Date subscribeDate;
	Date expiredDate;
	Book book;
	Subscriber subscriber;
	Set<DeliverySchedule> deliverySchedules;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public Set<DeliverySchedule> getDeliverySchedules() {
		return deliverySchedules;
	}
	public void setDeliverySchedules(Set<DeliverySchedule> deliverySchedules) {
		this.deliverySchedules = deliverySchedules;
	}
	public Integer getNoOfYear() {
		return noOfYear;
	}
	public void setNoOfYear(Integer noOfYear) {
		this.noOfYear = noOfYear;
	}
	public Date getSubscribeDate() {
		return subscribeDate;
	}
	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	
}
