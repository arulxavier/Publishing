package com.fixent.publish.client.book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;

import com.fixent.publish.client.book.view.BookPopupView;
import com.fixent.publish.client.book.view.BookView;
import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.service.impl.BookServiceImpl;

public class BookController extends BaseController {

	public BookView view;
	JDialog bookPopup;
	List<Book> books;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	Book book;

	public BookController() {

		view = new BookView();

		setView();

		view.getAddButton().addActionListener(new AddAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
		view.getViewButton().addActionListener(new ViewAction());
		view.getBookListTable().addMouseListener(new BookTableClickAction());
	}

	class AddAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			BookPopupView bookPopupView = new BookPopupView();

			bookPopupView.getSaveButton().addActionListener(
					new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().addActionListener(
					new CancelAction(bookPopupView));

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
			Book deleteObject = books.get(row);
			BookServiceImpl impl = new BookServiceImpl();
			if (!impl.deleteBook(deleteObject)) {

				setErrorMessages(view.getParent(),
						"Cannot delete book details after it is subscribed");
			}
			setView();
		}

	}

	class ViewAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			final int row = view.getBookListTable().getSelectedRow();
			// final int column =
			// view.getSubjectCategoryTable().getSelectedColumn();
			Book book = getBook((Integer) view.getBookListTable().getValueAt(
					row, 0));

			BookPopupView bookPopupView = new BookPopupView();
			bookPopupView.getBookNmaeTextField().setText(book.getName());
			bookPopupView.getAuthorTextField().setText(book.getAuthor());
			bookPopupView.getPublishingDatePicker().setDateTextField(
					book.getPublishingDate().toString());
			bookPopupView.getFrequencyComboBox().setSelectedItem(
					book.getFrequency());

			bookPopupView.getSaveButton().addActionListener(
					new SaveAction(bookPopupView));
			bookPopupView.getCancelButton().addActionListener(
					new CancelAction(bookPopupView));

			bookPopup = new JDialog();
			bookPopup.add(bookPopupView);
			bookPopup.setSize(450, 400);
			bookPopup.setResizable(false);
			bookPopup.setLocationRelativeTo(null);
			bookPopup.setVisible(true);

		}

	}

	class SaveAction implements ActionListener {

		BookPopupView bookPopupView;

		public SaveAction(BookPopupView bookPopupView) {

			this.bookPopupView = bookPopupView;
		}

		@SuppressWarnings("deprecation")
		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");

			BookServiceImpl impl = new BookServiceImpl();

			if (book == null) {
				book = new Book();
				book.setName(bookPopupView.getBookNmaeTextField().getText());
				book.setAuthor(bookPopupView.getAuthorTextField().getText());
				book.setPublishingDate(new Date(bookPopupView
						.getPublishingDatePicker().getjTextField1()));
				book.setFrequency(bookPopupView.getFrequencyComboBox()
						.getSelectedItem().toString());
				boolean result = checkForDuplicate(book.getName(), null, true);
				if (result) {
					setErrorMsg("Book Name already exist");
					return;
				}
				impl.createBook(book);
			} else {
				Book localBook = new Book();
				localBook.setId(book.getId());
				localBook.setName(bookPopupView.getBookNmaeTextField()
						.getText());
				localBook.setAuthor(bookPopupView.getAuthorTextField()
						.getText());
				localBook.setPublishingDate(new Date(bookPopupView
						.getPublishingDatePicker().getjTextField1()));
				localBook.setFrequency(bookPopupView.getFrequencyComboBox()
						.getSelectedItem().toString());
				boolean result = checkForDuplicate(localBook.getName(),
						localBook.getId(), false);
				if (result) {
					setErrorMsg("Book Name already exist");
					return;
				}
				impl.modifyBook(localBook);
			}

			setView();

			book = null;
			bookPopup.dispose();
		}

		private boolean checkForDuplicate(String bookName, Integer bookId,
				boolean isCreate) {
			boolean isDuplicate = false;
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			isDuplicate = bookServiceImpl.checkDuplicate(bookName, bookId,
					isCreate);
			return isDuplicate;
		}

	}

	class BookTableClickAction extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setErrorMsg("");
			if (e.getClickCount() == 2 && view.getBookListTable().getSelectedRow() > 0) {
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
						DATE_FORMAT.format(book.getPublishingDate()));
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

	Book getBook(int id) {

		for (Book book : books) {

			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

}
