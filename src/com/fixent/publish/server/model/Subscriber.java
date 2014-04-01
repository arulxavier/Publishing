package com.fixent.publish.server.model;

import java.util.Set;

public class Subscriber {
	
	Integer id;
	String name;
	String groupCode;
	String code;
	String mobileNumber;
	Address address;	
	
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
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
