package com.fixent.publish.client.subscribe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.SubscribeInfo;

public class SubscribeInfoDataTable extends AbstractTableModel {
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public SubscribeInfoDataTable(List<SubscribeInfo> subscribeInfos) {
		super();
		this.subscribeInfos = subscribeInfos;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<SubscribeInfo> subscribeInfos;
	String columnList[] = new String[] { "Book Name", "Subscribe Date", "No Of Year" };

	@Override
	public int getColumnCount() {
		return columnList.length;
	}

	@Override
	public int getRowCount() {
		return subscribeInfos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		SubscribeInfo entity = subscribeInfos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getBook().getName();
		case 1:
			return dateFormat.format(entity.getSubscribeDate());
		case 2:
			return entity.getNoOfYear();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int col) {
		return columnList[col];
	}

}
