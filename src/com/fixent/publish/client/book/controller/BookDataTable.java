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
	String columnList[] = new String[] { "Book ID", "Book Name", "Author", "Frequency" };

	@Override
	public int getColumnCount() {
		return columnList.length;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Book entity = books.get(rowIndex);
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
