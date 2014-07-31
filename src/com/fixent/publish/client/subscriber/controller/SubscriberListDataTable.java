package com.fixent.publish.client.subscriber.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Subscriber;

public class SubscriberListDataTable extends AbstractTableModel {

	public SubscriberListDataTable(List<Subscriber> studentList) {
		super();
		this.studentList = studentList;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Subscriber> studentList;
	String columnList[] = new String[] { "Name", "Addrress 1", "Address 2", "City", "Mobile Number", "Pincode" };

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return studentList != null ? studentList.size() : 0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		Subscriber entity = studentList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getName();
		case 1:
			return entity.getAddress().getStreet1();
		case 2:
			return entity.getAddress().getStreet2();
		case 3:
			return entity.getAddress().getCity();
		case 4:
			return entity.getAddress().getPincode();
		case 5:
			return entity.getMobileNumber();
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
