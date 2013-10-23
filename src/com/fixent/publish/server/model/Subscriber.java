package com.fixent.publish.server.model;

import java.util.Set;

public class Subscriber {
	
	int id;
	String name;
	String mobileNumber;
	Address address;
	Set<SubscribeInfo> subscribeInfos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<SubscribeInfo> getSubscribeInfos() {
		return subscribeInfos;
	}
	public void setSubscribeInfos(Set<SubscribeInfo> subscribeInfos) {
		this.subscribeInfos = subscribeInfos;
	}
	
	
}
