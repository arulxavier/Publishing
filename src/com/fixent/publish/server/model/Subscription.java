package com.fixent.publish.server.model;

import java.util.Date;

public class Subscription {
	
	Integer id;
	String subscriptionGroup;
	Integer subscriptionNumber;
	String subscriptionCode;
	Date subscriptionDate;
	Integer subscriptionYear;
	Date subscriptionExpiredDate;
	Book book;
	Subscriber subscriber;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubscriptionGroup() {
		return subscriptionGroup;
	}
	public void setSubscriptionGroup(String subscriptionGroup) {
		this.subscriptionGroup = subscriptionGroup;
	}
	public int getSubscriptionNumber() {
		return subscriptionNumber;
	}
	public void setSubscriptionNumber(int subscriptionNumber) {
		this.subscriptionNumber = subscriptionNumber;
	}
	public String getSubscriptionCode() {
		return subscriptionCode;
	}
	public void setSubscriptionCode(String subscriptionCode) {
		this.subscriptionCode = subscriptionCode;
	}
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public Integer getSubscriptionYear() {
		return subscriptionYear;
	}
	public void setSubscriptionYear(Integer subscriptionYear) {
		this.subscriptionYear = subscriptionYear;
	}
	public Date getSubscriptionExpiredDate() {
		return subscriptionExpiredDate;
	}
	public void setSubscriptionExpiredDate(Date subscriptionExpiredDate) {
		this.subscriptionExpiredDate = subscriptionExpiredDate;
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
}
