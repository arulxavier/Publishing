package com.fixent.publish.server.service;

public interface CodeBaseService {
	
	int getCodeValue(String code);
	
	boolean createCode(String code, int value);
	
	boolean updateCode(String code, int value);	
	
	String generateCode(String code);

}
