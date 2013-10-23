package com.fixent.publish.server.model;

public enum Frequency {
	
	MONTHLY("Monthly"), 
	QUARTERLY("Quarterly"), 
	SEMI_ANNUAL("Semi Annual"), 
	ANNUAL("Annual");
	 
	private String statusCode;
 
	private Frequency(String s) {
		statusCode = s;
	}
 
	public String getStatusCode() {
		return statusCode;
	}

}
