package com.fixent.publish.client.subscribe.controller;

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
	String columnList[] = new String[] { "Subscriber ID", "Subscriber Name" };

	@Override
	public int getColumnCount() {
		return columnList.length;
	}

	@Override
	public int getRowCount() {
		return studentList != null ? studentList.size() : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Subscriber entity = studentList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getId();
		case 1:
			return entity.getName();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int col) {
		return columnList[col];
	}

}
