package com.fixent.publish.client.notification.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.SubscribeInfo;

public class NotificationDataTable extends AbstractTableModel {

	public NotificationDataTable(List<SubscribeInfo> subscribers) {
		super();
		this.subscribers = subscribers;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<SubscribeInfo> subscribers;
	String columnList[] = new String[] { "Subscriber ID", "Subscriber Name",
			"Mobile Number", "Book Name" ,  "Expired Date", "Frequency"};

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return subscribers !=null ? subscribers.size() : 0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		SubscribeInfo entity = subscribers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getSubscriber() != null ? entity.getSubscriber().getId() : null;
		case 1:
			return entity.getSubscriber() != null ? entity.getSubscriber().getName() : null;
		case 2:
			return entity.getSubscriber() != null ?  entity.getSubscriber().getMobileNumber() : null;
		case 3:
			return entity.getBook()!= null ? entity.getBook().getName() : null;
		case 4:
			return entity.getExpiredDate()!= null ? entity.getExpiredDate() : null;
		case 5:
			return entity.getBook()!= null ? entity.getBook().getFrequency() : null;
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
