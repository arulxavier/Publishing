package com.fixent.publish.server.model.info;

import java.util.Date;

public class SubscriptionInfo {

	String subscriptionCode;
	
	String subscriptionGroup;
	
	String bookName;
	
	Date fromDate;
	
	Date toDate;
	
	boolean freeCopy;
	
	boolean exchangeCopy;

	public String getSubscriptionCode() {
		return subscriptionCode;
	}

	public void setSubscriptionCode(String subscriptionCode) {
		this.subscriptionCode = subscriptionCode;
	}

	public String getSubscriptionGroup() {
		return subscriptionGroup;
	}

	public void setSubscriptionGroup(String subscriptionGroup) {
		this.subscriptionGroup = subscriptionGroup;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean isFreeCopy() {
		return freeCopy;
	}

	public void setFreeCopy(boolean freeCopy) {
		this.freeCopy = freeCopy;
	}

	public boolean isExchangeCopy() {
		return exchangeCopy;
	}

	public void setExchangeCopy(boolean exchangeCopy) {
		this.exchangeCopy = exchangeCopy;
	}
	
}
