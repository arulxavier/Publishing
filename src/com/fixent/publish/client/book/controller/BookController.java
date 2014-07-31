package com.fixent.publish.client.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;

import com.fixent.publish.client.book.view.BookPopupView;
import com.fixent.publish.client.book.view.BookView;
import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.info.BookInfo;
import com.fixent.publish.server.service.impl.BookServiceImpl;

public class BookController 
extends BaseController {

	public BookView view;
	JDialog bookPopup;
	List<Book> books;	
	Book book;

	public BookController() {

		initView();
		subscribeListner();
	}


	private void initView() {
		
		view = new BookView();
		setView();
	}

	private void subscribeListner() {
		
		view.getAddButton().addActionListener(new AddAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
		view.getViewButton().addActionListener(new ViewAction());
		view.getBookListTable().addMouseListener(new BookTableClickAction());
	}
	
	class AddAction 
	implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			BookPopupView bookPopupView = new BookPopupView();
			bookPopupView.getPublishingDatePicker().setDateTextField(new Date());
			
			bookPopupView.getSaveButton().addActionListener(new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().addActionListener(new CancelAction(bookPopupView));
			
			bookPopupView.getAuthorTextField().setVisible(false);
			bookPopupView.getPublishingDatePicker().setVisible(false);
			bookPopupView.getjLabel5().setVisible(false);
			bookPopupView.getjLabel6().setVisible(false);
			

			bookPopup = new JDialog();
			bookPopup.add(bookPopupView);
			bookPopup.setSize(450, 400);
			bookPopup.setResizable(false);
			bookPopup.setLocationRelativeTo(null);
			bookPopup.setVisible(true);
		}
	}

	class DeleteAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			
			final int row = view.getBookListTable().getSelectedRow();
			
			if (row != -1) {
				
				Book deleteObject = books.get(row);
				BookServiceImpl impl = new BookServiceImpl();
				
				if (!impl.deleteBook(deleteObject)) {
					setErrorMsg("Cannot delete book details after it is subscribed");
				}
				setView();
			} else {
				setErrorMsg("Select Row before Delete");
			}
		}
	}

	class ViewAction 
	implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			final int row = view.getBookListTable().getSelectedRow();
			
			if (row != -1) {
				
				book = getBook((String) view.getBookListTable().getValueAt(row, 0));
				
				BookPopupView bookPopupView = new BookPopupView();
				bookPopupView.getBookNmaeTextField().setText(book.getName());
				bookPopupView.getAuthorTextField().setText(book.getAuthor());
				bookPopupView.getPublishingDatePicker().setDateTextField(
						book.getPublishingDate());
				bookPopupView.getFrequencyComboBox().setSelectedItem(
						book.getFrequency());
				
				bookPopupView.getSaveButton().addActionListener(
						new SaveAction(bookPopupView));
				bookPopupView.getCancelButton().addActionListener(
						new CancelAction(bookPopupView));
				
				bookPopupView.getAuthorTextField().setVisible(false);
				bookPopupView.getPublishingDatePicker().setVisible(false);
				bookPopupView.getjLabel5().setVisible(false);
				bookPopupView.getjLabel6().setVisible(false);
				
				
				bookPopup = new JDialog();
				bookPopup.add(bookPopupView);
				bookPopup.setSize(450, 400);
				bookPopup.setResizable(false);
				bookPopup.setLocationRelativeTo(null);
				bookPopup.setVisible(true);
			} else {
				setErrorMsg("Select Row before Edit");
			}
		}
	}

	class SaveAction 
	implements ActionListener {

		BookPopupView bookPopupView;

		public SaveAction(BookPopupView bookPopupView) {
			this.bookPopupView = bookPopupView;
		}

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			String publishdate;

			BookServiceImpl impl = new BookServiceImpl();
			
			if (validateBook()) {
				
				if (book == null) {
					
					book = new Book();
					book.setName(bookPopupView.getBookNmaeTextField().getText());
					book.setAuthor(bookPopupView.getAuthorTextField().getText());
					publishdate = bookPopupView.getPublishingDatePicker().getjTextField1();
					book.setPublishingDate(new Date(publishdate));
					book.setFrequency(bookPopupView.getFrequencyComboBox().getSelectedItem().toString());
					
					BookInfo bookInfo = new BookInfo();
					bookInfo.setBookName(book.getName());
					bookInfo.setIsCreate(true);
					boolean result = checkForDuplicate(bookInfo);
					if (result) {
						setErrorMsg("Book Name already exist");
						return;
					}
					impl.createBook(book);
				} else {
					
					book.setName(bookPopupView.getBookNmaeTextField()
							.getText());
					book.setAuthor(bookPopupView.getAuthorTextField()
							.getText());
					publishdate = bookPopupView
							.getPublishingDatePicker().getjTextField1();
					Date date = new Date(publishdate);
					book.setPublishingDate(date);
					book.setFrequency(bookPopupView.getFrequencyComboBox()
							.getSelectedItem().toString());
					
					BookInfo bookInfo = new BookInfo();
					bookInfo.setBookName(book.getName());
					bookInfo.setIsCreate(false);
					bookInfo.setId(book.getId());
					
					boolean result = checkForDuplicate(bookInfo);
					if (result) {
						setErrorMsg("Book Name already exist");
						return;
					}
					impl.modifyBook(book);
				}
				
				setView();
				
				book = null;
				bookPopup.dispose();
			}

		}

		private boolean validateBook() {
			
			StringBuffer errorMsg = new StringBuffer();
			boolean status = false;
			errorMsg.append("Enter Mandatory Fields : ");
			
			if (bookPopupView.getBookNmaeTextField().getText().length() == 0) {
				
				errorMsg.append("Book Name, ");
				status = true;
			}
			if (bookPopupView.getFrequencyComboBox().getSelectedItem().toString().equalsIgnoreCase("Select one")) {
				
				errorMsg.append("Frequency Year");
				status = true;
			}
			
			if (status) {
				
				bookPopupView.getErrorLabel().setText(errorMsg.toString());
				return false;
			} else {				
				return true;
			}
			
		}

		private boolean checkForDuplicate(BookInfo bookInfo) {
			boolean isDuplicate = false;
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			isDuplicate = bookServiceImpl.checkDuplicate(bookInfo);
			return isDuplicate;
		}

	}

	class BookTableClickAction 
	extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setErrorMsg("");
			if (e.getClickCount() == 2 && view.getBookListTable().getSelectedRow() >= 0) {
				
				final int row = view.getBookListTable().getSelectedRow();
				
				book = books.get(row);
				BookPopupView bookView = new BookPopupView();
				bookView.getSaveButton().addActionListener(
						new SaveAction(bookView));
				bookView.getCancelButton().addActionListener(
						new CancelAction(bookView));
				bookView.getFrequencyComboBox().setSelectedItem(
						book.getFrequency());
				bookView.getBookNmaeTextField().setText(book.getName());
				bookView.getAuthorTextField().setText(book.getAuthor());
				bookView.getPublishingDatePicker().setDateTextField(
						book.getPublishingDate());
				setEditMode(bookView, false);
				
				bookView.getAuthorTextField().setVisible(false);
				bookView.getPublishingDatePicker().setVisible(false);
				bookView.getjLabel5().setVisible(false);
				bookView.getjLabel6().setVisible(false);
				
				bookPopup = new JDialog();
				bookPopup.add(bookView);
				bookPopup.setSize(400, 400);
				bookPopup.setLocationRelativeTo(null);
				bookPopup.setVisible(true);
			}
		}
	}

	private void setErrorMsg(String msg) {
		view.getErrorLabel().setText(msg);
	}

	public void setView() {
		
		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		books = bookServiceImpl.getBooks();
		BookDataTable dataTable = new BookDataTable(books);
		view.getBookListTable().setModel(dataTable);
	}
	
	public void setEditMode(BookPopupView popupView, Boolean status) {
		
		popupView.getBookNmaeTextField().setEditable(status);
		popupView.getAuthorTextField().setEditable(status);
		popupView.getPublishingDatePicker().setEditable(status);
		popupView.getFrequencyComboBox().setEnabled(status);
		
	}

	class CancelAction implements ActionListener {

		BookPopupView bookPopupView;

		public CancelAction(BookPopupView bookPopupView) {

			this.bookPopupView = bookPopupView;
		}

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			book = null;
			bookPopupView.getSaveButton().removeActionListener(
					new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().removeActionListener(
					new CancelAction(bookPopupView));
			bookPopup.dispose();
		}

	}

	Book getBook(String bookName) {

		for (Book book : books) {

			if (book.getName() == bookName) {
				return book;
			}
		}
		return null;
	}

}
