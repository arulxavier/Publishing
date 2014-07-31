package com.fixent.publish.client.common;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class BaseController {
	
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static Map<String, Object> OBJECT_MAP = new HashMap<String, Object>();
	
	public static void push(String key, Object value)
	{
		OBJECT_MAP.put(key, value);
	}

	public static Object pop(String key)
	{
		return OBJECT_MAP.remove(key);
	}
	
	public void showPopup(Component component, String msg) {
		
		JOptionPane.showMessageDialog(component, msg);
	}
	
	

}
