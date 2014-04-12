package com.fixent.publish.client.subscriber.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Subscription;

public class SubscribeInfoDataTable extends AbstractTableModel {
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public SubscribeInfoDataTable(List<Subscription> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Subscription> subscribeInfos;
	String columnList[] = new String[] { "Book Name", "Subscribed Date", "Years" };

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return subscribeInfos.size();
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		Subscription entity = subscribeInfos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getBook().getName();
		case 1:
			return dateFormat.format(entity.getSubscriptionDate());
		case 2:
			return entity.getSubscriptionYear();
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
