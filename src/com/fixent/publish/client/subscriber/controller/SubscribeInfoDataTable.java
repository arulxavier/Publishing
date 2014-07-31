package com.fixent.publish.client.subscriber.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Subscription;

public class SubscribeInfoDataTable extends AbstractTableModel {
	
	DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");

	public SubscribeInfoDataTable(List<Subscription> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Subscription> subscribeInfos;
	String columnList[] = new String[] { "Book Name", "Group Code", "Subscription Year", "Expiry Date", "Free Copy", "ExchangeCopy"};

	
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
			return entity.getSubscriptionCode();
		case 2:
			return entity.getSubscriptionYear();
		case 3:
			return dateFormat.format(entity.getSubscriptionExpiredDate());
		case 4:
			return entity.isFreeCopy() ? "Yes" : "NO";
		case 5:
			return entity.isExchangeCopy() ? "Yes" : "NO";
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
