
package com.fixent.publish.client.book.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Book;

public class BookDataTable extends AbstractTableModel {

	public BookDataTable(List<Book> books) {
		super();
		this.books = books;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Book> books;
	String columnList[] = new String[] {"Book Name", "Frequency" };

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return books != null ? books.size():0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		Book entity = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getName();
		case 1:
			return entity.getFrequency();
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
