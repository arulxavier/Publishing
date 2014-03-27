package com.fixent.publish.client.common;

import java.util.HashMap;
import java.util.Map;

public class Menu {
	
	Map<String, String> rootMenu = new HashMap<String, String>();
	Map<String, Map<String, String>> subMenuMap = new HashMap<String, Map<String,String>>();
	Map<String, String> masterSubMenu = new HashMap<String, String>();
	
	public Menu() {
		
		rootMenu.put("1", "Publish");

		masterSubMenu.put("1", "Book");
		masterSubMenu.put("2", "Subscriber");
		masterSubMenu.put("3", "Publish Edition");
		masterSubMenu.put("4", "Delivery");
		masterSubMenu.put("5", "Notification");

		
		subMenuMap.put("Publish", masterSubMenu);
		
	}

	public Map<String, String> getRootMenu() {
		return rootMenu;
	}

	public void setRootMenu(Map<String, String> rootMenu) {
		this.rootMenu = rootMenu;
	}

	public Map<String, Map<String, String>> getSubMenuMap() {
		return subMenuMap;
	}

	public void setSubMenuMap(Map<String, Map<String, String>> subMenuMap) {
		this.subMenuMap = subMenuMap;
	}

	
	

}
