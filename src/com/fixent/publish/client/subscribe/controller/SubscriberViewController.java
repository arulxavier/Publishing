package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightSidePanel;
import com.fixent.publish.client.subscribe.view.SubscriberInfoPopupView;
import com.fixent.publish.client.subscribe.view.SubscriberView;
import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.BookServiceImpl;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberViewController extends BaseController {

	SubscriberView view;
	List<Book> books;
	JDialog subscribeInfoPopup;
	List<SubscribeInfo> subscribeInfos = new ArrayList<SubscribeInfo>();
	SubscribeInfo subscribeInfo;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

	public SubscriberViewController() {

		view = new SubscriberView();

		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		books = bookServiceImpl.getBooks();
		SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
				subscribeInfos);
		view.getSubscribeInfoTable().setModel(dataModel);
		view.getAddButton().addActionListener(new AddAction());
		view.getSubscribeInfoTable().addMouseListener(
				new SubjectTableClickAction());
		view.getSaveButton().addActionListener(new SaveAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
	}

	class SubjectTableClickAction extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setErrorMessages(view.getParent(), "");
			if (e.getClickCount() == 2) {
				final int row = view.getSubscribeInfoTable().getSelectedRow();
				subscribeInfo = subscribeInfos.get(row);
				SubscriberInfoPopupView infoPopupView = new SubscriberInfoPopupView();
				infoPopupView.getSaveButton().addActionListener(
						new SavePopupAction(infoPopupView));
				infoPopupView.getCancelButton().addActionListener(
						new CancelAction(infoPopupView));
				infoPopupView.getNoOfYearComboBox().addItemListener(
						new YearChangeEvent(infoPopupView));
				infoPopupView.getSubscribeDatePicker().getDateField()
						.addCaretListener(new SubscribedAction(infoPopupView));
				DefaultComboBoxModel boxModel = new DefaultComboBoxModel();

				boxModel.addElement("Select One");

				for (Book book : books) {

					boxModel.addElement(book.getName());
				}

				infoPopupView.getBookComboBox().setModel(boxModel);

				infoPopupView.getBookComboBox().setSelectedItem(
						subscribeInfo.getBook().getName());
				infoPopupView.getNoOfYearComboBox().setSelectedItem(
						String.valueOf(subscribeInfo.getNoOfYear()));
				infoPopupView.getSubscribeDatePicker().setDateTextField(
						DATE_FORMAT.format(subscribeInfo.getSubscribeDate()));

				infoPopupView.getExpiryTxt().setText(
						DATE_FORMAT.format(subscribeInfo.getExpiredDate())
								.toString());

				subscribeInfoPopup = new JDialog();
				subscribeInfoPopup.add(infoPopupView);
				subscribeInfoPopup.setSize(540, 215);
				subscribeInfoPopup.setResizable(false);
				subscribeInfoPopup.setLocationRelativeTo(null);
				subscribeInfoPopup.setVisible(true);
			}
		}
	}

	class SubscribedAction implements CaretListener {
		SubscriberInfoPopupView infoPopupView;

		public SubscribedAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void caretUpdate(CaretEvent e) {

			setErrorMessages(view.getParent(), "");
			setExpiryDateValue(infoPopupView);
		}
	}

	class AddAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMessages(view.getParent(), "");
			SubscriberInfoPopupView infoPopupView = new SubscriberInfoPopupView();
			infoPopupView.getSaveButton().addActionListener(
					new SavePopupAction(infoPopupView));
			infoPopupView.getCancelButton().addActionListener(
					new CancelAction(infoPopupView));
			infoPopupView.getNoOfYearComboBox().addItemListener(
					new YearChangeEvent(infoPopupView));
			infoPopupView.getSubscribeDatePicker().getDateField()
					.addCaretListener(new SubscribedAction(infoPopupView));

			DefaultComboBoxModel boxModel = new DefaultComboBoxModel();

			boxModel.addElement("Select One");

			for (Book book : books) {

				boxModel.addElement(book.getName());
			}
			infoPopupView.getBookComboBox().setModel(boxModel);

			subscribeInfoPopup = new JDialog();
			subscribeInfoPopup.add(infoPopupView);
			subscribeInfoPopup.setSize(540, 215);
			subscribeInfoPopup.setResizable(false);
			subscribeInfoPopup.setLocationRelativeTo(null);
			subscribeInfoPopup.setVisible(true);

		}

	}

	class DeleteAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMessages(view.getParent(), "");
			final int row = view.getSubscribeInfoTable().getSelectedRow();
			subscribeInfos.remove(row);
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
					subscribeInfos);
			view.getSubscribeInfoTable().setModel(dataModel);
		}

	}

	class SaveAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMessages(view.getParent(), "");
			boolean result = validateMandatoryFields();
			if (!result) {
				setErrorMessages(view.getParent(), "");
				return;
			}
			Subscriber subscriber = new Subscriber();
			subscriber.setName(view.getSubscriberNameTextField().getText());
			subscriber.setMobileNumber(view.getMobileNumberTextField()
					.getText());

			Address address = new Address();
			address.setStreet(view.getStreetTextField().getText());
			address.setCity(view.getCityTextField().getText());
			address.setState(view.getStateTextField().getText());
			address.setCountry(view.getCountryTextField().getText());
			address.setPincode(Integer.parseInt(view.getPincodeTextField()
					.getText()));
			subscriber.setAddress(address);

			Set<SubscribeInfo> infoSet = new HashSet<SubscribeInfo>();
			infoSet.addAll(subscribeInfos);
			subscriber.setSubscribeInfos(infoSet);

			SubscribeServiceImpl impl = new SubscribeServiceImpl();
			impl.createSubscriber(subscriber);

			RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);

		}

		private boolean validateMandatoryFields() {

			String errorMsgs = null;
			errorMsgs = checkEmpty("Subscriber Name is mandatory", view
					.getSubscriberNameTextField().getText());
			errorMsgs = checkEmpty("Pincode is mandatory", view
					.getSubscriberNameTextField().getText());
			try {
				Integer.parseInt(view.getPincodeTextField().getText());
			} catch (NumberFormatException e) {
				errorMsgs.concat("Enter a numeric value for Pincode");
			}
			return true;
		}

		private String checkEmpty(String erroMsg, String text) {

			String errorMsgs = null;
			if (text == null && text.isEmpty()) {
				errorMsgs.concat(erroMsg);
			}
			return errorMsgs;
		}
	}

	class SavePopupAction implements ActionListener {

		SubscriberInfoPopupView infoPopupView;

		public SavePopupAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		@SuppressWarnings("deprecation")
		
		public void actionPerformed(ActionEvent e) {

			setErrorMessages(view.getParent(), "");
			SubscribeInfo info = new SubscribeInfo();
			info.setBook(getBook(infoPopupView.getBookComboBox()
					.getSelectedItem().toString()));
			info.setSubscribeDate(new Date(infoPopupView
					.getSubscribeDatePicker().getjTextField1().toString()));
			info.setNoOfYear(Integer.parseInt(infoPopupView
					.getNoOfYearComboBox().getSelectedItem().toString()));
			info.setExpiredDate(new Date(infoPopupView.getExpiryTxt().getText()
					.toString()));
			if (subscribeInfos != null && info.getBook().getName() != null) {
				for (SubscribeInfo info2 : subscribeInfos) {
					if (info2.getBook().getName()
							.equalsIgnoreCase(info.getBook().getName())) {
						setErrorMessages(view.getParent(),
								"Book is already subscribed for the same subscriber");
						return;
					}
				}
			}
			subscribeInfos.add(info);
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
					subscribeInfos);
			view.getSubscribeInfoTable().setModel(dataModel);
			subscribeInfoPopup.dispose();

		}

	}

	class CancelAction implements ActionListener {

		SubscriberInfoPopupView infoPopupView;

		public CancelAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void actionPerformed(ActionEvent e) {

			setErrorMessages(view.getParent(), "");
			subscribeInfoPopup.dispose();
		}

	}

	class YearChangeEvent implements ItemListener {

		SubscriberInfoPopupView infoPopupView;

		public YearChangeEvent(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		@SuppressWarnings("deprecation")
		
		public void itemStateChanged(ItemEvent e) {
			setExpiryDateValue(infoPopupView);
		}

	}

	public void setExpiryDateValue(SubscriberInfoPopupView infoPopupView) {

		if (infoPopupView.getSubscribeDatePicker().getjTextField1() != null
				&& !infoPopupView.getSubscribeDatePicker().getjTextField1()
						.isEmpty()
				&& infoPopupView.getNoOfYearComboBox().getSelectedItem() != null
				&& !infoPopupView.getNoOfYearComboBox().getSelectedItem()
						.toString().equals("Select One")) {
			int noOfYear = Integer.parseInt(infoPopupView.getNoOfYearComboBox()
					.getSelectedItem().toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(infoPopupView.getSubscribeDatePicker()
					.getjTextField1()));
			calendar.add(Calendar.YEAR, noOfYear);
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DATE,
					calendar.getActualMaximum(Calendar.DATE));
			DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
			infoPopupView.getExpiryTxt().setText(
					DATE_FORMAT.format(calendar.getTime()));
		}
	}

	public Book getBook(String bookName) {
		for (Book book : books) {
			if (book.getName().equalsIgnoreCase(bookName)) {
				return book;
			}
		}
		return null;
	}
}
