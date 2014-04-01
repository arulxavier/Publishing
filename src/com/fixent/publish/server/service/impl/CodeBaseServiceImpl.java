package com.fixent.publish.server.service.impl;

import com.fixent.publish.server.dao.CodeBaseDAO;
import com.fixent.publish.server.service.CodeBaseService;

public class CodeBaseServiceImpl 
implements CodeBaseService {

	public int getCodeValue(String code) {
		
		int value = 0;
		try {
			
			CodeBaseDAO dao = new CodeBaseDAO();
			value =  dao.getCodeValue(code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public boolean createCode(String code, int value) {
		
		try {
			
			CodeBaseDAO dao = new CodeBaseDAO();
			return dao.createCode(code, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateCode(String code, int value) {
		
		try {
			
			CodeBaseDAO dao = new CodeBaseDAO();
			return dao.updateCode(code, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String generateCode(String code) {
		
		
		try {
			
			int value = getCodeValue(code);
			if (0 == value) {
				
				createCode(code, value+1);
			} else {
				updateCode(code, value+1);
			}
			
			value = value + 1;
			return code + value;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	

}
