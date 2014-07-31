package com.fixent.publish.client.search.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.Subscriber;

public class SubscriptionDataTable extends AbstractTableModel {

	public SubscriptionDataTable(List<Subscription> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Subscription> subscribeInfos;
	String columnList[] = new String[] { "Subscription Code", "Book Name", "Expiry date",
			"Subscriber Name", "Mobile Number"};

	
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

		Subscription entity = subscribeInfos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getSubscriptionCode() != null ? entity.getSubscriptionCode().trim() : null;
		case 1:
			return entity.getBook() != null ? entity.getBook().getName().trim() : null;
		case 2:
			return entity != null ? getExpiryDate(entity.getSubscriptionExpiredDate()) : null;
		case 3:
			return entity.getSubscriber() != null ?  entity.getSubscriber().getName().trim() : null;
		case 4:
			return entity.getSubscriber() != null ?  entity.getSubscriber().getMobileNumber().trim() : null;
		default:
			return null;
		}
	}
	
	public String getExpiryDate(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		return dateFormat.format(date);
	}
	
	public String getBook(Subscriber subscriber) {
		
		StringBuffer buffer = new StringBuffer();
		for (Subscription info : subscriber.getSubscriptions()) {
			
			buffer.append(info.getBook().getName() + " : \n" );
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			buffer.append(dateFormat.format(info.getSubscriptionExpiredDate()) + " \n");
			
		}
		return buffer.toString();
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
