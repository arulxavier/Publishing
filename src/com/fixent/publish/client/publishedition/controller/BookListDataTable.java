package com.fixent.publish.client.publishedition.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.fixent.publish.server.model.Book;

public class BookListDataTable extends AbstractTableModel {

	public BookListDataTable(List<Book> books) {
		super();
		this.books = books;
	}

	/**/
	private static final long serialVersionUID = 1L;
	List<Book> books;
	
	String columnList[] = new String[] { 
										"Book ID", 
										"Book Name",
										"Author", 
										"Frequency"
										};

	
	public int getColumnCount() {
		return columnList.length;
	}

	
	public int getRowCount() {
		return books !=null ? books.size() : 0;
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {

		Book entity = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return entity.getId();
		case 1:
			return entity.getName();
		case 2:
			return entity.getAuthor();
		case 3:
			return entity.getFrequency();		
		default:
			return null;
		}
	}

	
	public String getColumnName(int col) {
		return columnList[col];
	}

}
