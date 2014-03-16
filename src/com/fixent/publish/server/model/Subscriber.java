package com.fixent.publish.server.model;

import java.util.Set;

public class Subscriber {
	
	Integer id;
	String name;
	String mobileNumber;
	String street;
	String city;
	String state;
	String country;
	Integer pincode;
	
	Set<SubscribeInfo> subscribeInfos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Set<SubscribeInfo> getSubscribeInfos() {
		return subscribeInfos;
	}
	public void setSubscribeInfos(Set<SubscribeInfo> subscribeInfos) {
		this.subscribeInfos = subscribeInfos;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	
}
