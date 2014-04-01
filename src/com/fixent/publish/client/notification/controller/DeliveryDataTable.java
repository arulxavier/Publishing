package com.fixent.publish.client.notification.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;

public class DeliveryDataTable extends AbstractTableModel {

	public DeliveryDataTable(List<SubscribeInfo> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<SubscribeInfo> subscribeInfos;
	String columnList[] = new String[] { "Book Name", "Expiry date",
			"Subscriber Name", "Mobile Number" , "Address"};

	
	public int getColumnCount() {
		return columnList.length;
	}

	public String[] getColumnList() {
		return columnList;
	}
	public int getRowCount() {
		return subscribeInfos !=null ? subscribeInfos.size() : 0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		SubscribeInfo entity = subscribeInfos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getBook() != null ? entity.getBook().getName().trim() : null;
		case 1:
			return entity != null ? getExpiryDate(entity.getExpiredDate()) : null;
		case 2:
			return entity.getSubscriber() != null ?  entity.getSubscriber().getName().trim() : null;
		case 3:
			return entity.getSubscriber() != null ?  entity.getSubscriber().getMobileNumber().trim() : null;
		case 4:
			return entity.getSubscriber().getAddress() != null ? entity.getSubscriber().getAddress().getPincode() : null;		
		default:
			return null;
		}
	}
	
	public String getExpiryDate(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(date);
	}
	
	public String getBook(Subscriber subscriber) {
		
		StringBuffer buffer = new StringBuffer();
		for (SubscribeInfo info : subscriber.getSubscribeInfos()) {
			
			buffer.append(info.getBook().getName() + " : \n" );
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			buffer.append(dateFormat.format(info.getExpiredDate()) + " \n");
			
		}
		return buffer.toString();
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
