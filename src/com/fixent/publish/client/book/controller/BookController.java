package com.fixent.publish.client.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;

import com.fixent.publish.client.book.view.BookPopupView;
import com.fixent.publish.client.book.view.BookView;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.service.impl.BookServiceImpl;

public class BookController {

	public BookView view;
	JDialog bookPopup;
	List<Book> books;

	public BookController() {

		view = new BookView();

		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		books = bookServiceImpl.getBooks();
		BookDataTable dataTable = new BookDataTable(books);
		view.getBookListTable().setModel(dataTable);

		view.getAddButton().addActionListener(new AddAction());
		view.getViewButton().addActionListener(new ViewAction());
	}


	class AddAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			BookPopupView bookPopupView = new BookPopupView();

			bookPopupView.getSaveButton().addActionListener(new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().addActionListener(new CancelAction(bookPopupView));

			bookPopup = new JDialog();
			bookPopup.add(bookPopupView);
			bookPopup.setSize(450, 400);
			bookPopup.setResizable(false);			
			bookPopup.setLocationRelativeTo(null);
			bookPopup.setVisible(true);

		}

	}

	class DeleteAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class ViewAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final int row = view.getBookListTable().getSelectedRow();
			//	          final int column = view.getSubjectCategoryTable().getSelectedColumn();
			Book book = getBook((Integer)view.getBookListTable().getValueAt(row, 0));


			BookPopupView bookPopupView = new BookPopupView();
			bookPopupView.getBookIdTextField().setText((String.valueOf(book.getId())));
			bookPopupView.getBookNmaeTextField().setText(book.getName());
			bookPopupView.getAuthorTextField().setText(book.getAuthor());
			bookPopupView.getPublishingDatePicker().setDateTextField(book.getPublishingDate().toString());
			bookPopupView.getFrequencyComboBox().setSelectedItem(book.getFrequency());
			

			bookPopupView.getSaveButton().addActionListener(new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().addActionListener(new CancelAction(bookPopupView));

			bookPopup = new JDialog();
			bookPopup.add(bookPopupView);
			bookPopup.setSize(450, 400);
			bookPopup.setResizable(false);			
			bookPopup.setLocationRelativeTo(null);
			bookPopup.setVisible(true);


		}

	}

	class SaveAction 
	implements ActionListener {

		BookPopupView bookPopupView;

		public SaveAction(BookPopupView bookPopupView) {

			this.bookPopupView = bookPopupView;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {

			Book book = new Book();
			book.setName(bookPopupView.getBookNmaeTextField().getText());
			book.setAuthor(bookPopupView.getAuthorTextField().getText());
			book.setPublishingDate(new Date(bookPopupView.getPublishingDatePicker().getjTextField1()));
			book.setFrequency(bookPopupView.getFrequencyComboBox().getSelectedItem().toString());

			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			bookServiceImpl.createBook(book);
			bookPopup.dispose();
		}

	}

	class CancelAction
	implements ActionListener {

		BookPopupView bookPopupView;

		public CancelAction(BookPopupView bookPopupView) {

			this.bookPopupView = bookPopupView;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			bookPopupView.getSaveButton().removeActionListener(new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().removeActionListener(new CancelAction(bookPopupView));
			bookPopup.dispose();
		}

	}

	Book getBook(int id) {

		for(Book book : books) {

			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

}
