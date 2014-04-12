package com.fixent.publish.client.publishedition.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.Subscriber;

public class SubscriberDataTable extends AbstractTableModel {

	public SubscriberDataTable(List<Subscription> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Subscription> subscribeInfos;
	
	String columnList[] = new String[] { 
										"Subscriber ID", 
										"Subscriber Name",
										"Mobile Number"
										};

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return subscribeInfos !=null ? subscribeInfos.size() : 0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		Subscription entity = subscribeInfos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getId();
		case 1:
			return entity.getSubscriber().getName();
		case 2:
			return entity.getSubscriber().getMobileNumber();				
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
