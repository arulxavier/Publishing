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
import com.fixent.publish.client.comon.RightPanel;
import com.fixent.publish.client.subscribe.view.SubscriberInfoPopupView;
import com.fixent.publish.client.subscribe.view.SubscriberView;
import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.BookServiceImpl;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberController extends BaseController {

	SubscriberView view;
	List<Book> books;
	JDialog subscribeInfoPopup;
	List<SubscribeInfo> subscribeInfos = new ArrayList<SubscribeInfo>();
	SubscribeInfo subscribeInfo;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	public static String ADD = "add";
	public static String VIEW = "view";
	public static String MODIFY = "modify";
	public static String SCREEN_MODE = "screenmode";
	String screenMode;
	Subscriber subscriber;
	Boolean isAdd;

	public SubscriberController() {

		view = new SubscriberView();
		screenMode = (String) pop(SCREEN_MODE);
		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		books = bookServiceImpl.getBooks();
		SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
				subscribeInfos);
		view.getSubscribeInfoTable().setModel(dataModel);
		if (ADD.equalsIgnoreCase(screenMode)) {

		} else if (MODIFY.equalsIgnoreCase(screenMode)) {

			subscriber = (Subscriber) OBJECT_MAP.get("subscriber");
			setView();
		} else if (VIEW.equalsIgnoreCase(screenMode)) {

			subscriber = (Subscriber) OBJECT_MAP.get("subscriber");
			setView();
			setViewMode();
		}

		view.getAddButton().addActionListener(new AddAction());
		view.getSubscribeInfoTable().addMouseListener(
				new SubjectTableClickAction());
		view.getSaveButton().addActionListener(new SaveAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
		view.getCancelBtn().addActionListener(new MainCancelAction());
	}

	private void setViewMode() {
		view.getSubscriberNameTextField().setEditable(false);
		view.getMobileNumberTextField().setEditable(false);
		view.getStreetTextField().setEditable(false);
		view.getCityTextField().setEditable(false);
		view.getStateTextField().setEditable(false);
		view.getCountryTextField().setEditable(false);
		view.getPincodeTextField().setEditable(false);
		view.getAddButton().setEnabled(false);
		view.getDeleteButton().setEnabled(false);
		view.getSaveButton().setEnabled(false);
	}

	public void setView() {
		if (subscriber != null) {
			view.getSubscriberNameTextField().setText(subscriber.getName());
			view.getMobileNumberTextField().setText(
					subscriber.getMobileNumber());
			view.getStreetTextField().setText(subscriber.getAddress().getStreet());
			view.getCityTextField().setText(subscriber.getAddress().getCity());
			view.getStateTextField().setText(subscriber.getAddress().getState());
			view.getCountryTextField().setText(subscriber.getAddress().getCountry());
			view.getPincodeTextField().setText(
					String.valueOf(subscriber.getAddress().getPincode()));
			if (subscriber.getSubscribeInfos() != null) {
				subscribeInfos = new ArrayList<SubscribeInfo>(
						subscriber.getSubscribeInfos());
				SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
						subscribeInfos);
				view.getSubscribeInfoTable().setModel(dataModel);
			}
		}

	}

	class SubjectTableClickAction extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setErrorMsg("");
			if (e.getClickCount() == 2) {
				final int row = view.getSubscribeInfoTable().getSelectedRow();
				subscribeInfo = subscribeInfos.get(row);
				SubscriberInfoPopupView infoPopupView = new SubscriberInfoPopupView();
				setPopUpErrorMsg("", infoPopupView);
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

				if (VIEW.equalsIgnoreCase(screenMode)) {
					infoPopupView.getSaveButton().setEnabled(false);
					infoPopupView.getBookComboBox().setEnabled(false);
					infoPopupView.getSubscribeDatePicker().setEditable(false);
					infoPopupView.getNoOfYearComboBox().setEnabled(false);
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
				subscribeInfoPopup.setSize(550, 250);
				subscribeInfoPopup.setResizable(false);
				subscribeInfoPopup.setLocationRelativeTo(null);
				subscribeInfoPopup.setVisible(true);
				isAdd = false;
			}
		}
	}

	class SubscribedAction implements CaretListener {
		SubscriberInfoPopupView infoPopupView;

		public SubscribedAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void caretUpdate(CaretEvent e) {

			setErrorMsg("");
			setPopUpErrorMsg("", infoPopupView);
			setExpiryDateValue(infoPopupView);
		}
	}

	class AddAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			SubscriberInfoPopupView infoPopupView = new SubscriberInfoPopupView();
			setPopUpErrorMsg("", infoPopupView);
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
			subscribeInfoPopup.setSize(550, 250);
			subscribeInfoPopup.setResizable(false);
			subscribeInfoPopup.setLocationRelativeTo(null);
			subscribeInfoPopup.setVisible(true);
			isAdd = true;
		}
	}

	class MainCancelAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			/*RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);*/
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}

	class DeleteAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			final int row = view.getSubscribeInfoTable().getSelectedRow();
			subscribeInfos.remove(row);
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
					subscribeInfos);
			view.getSubscribeInfoTable().setModel(dataModel);
		}

	}

	class SaveAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			boolean result = validateMandatoryFields();
			if (!result) {
				return;
			}
			if (MODIFY.equalsIgnoreCase(screenMode)) {
				result = checkForDuplicate(view.getSubscriberNameTextField()
						.getText(), subscriber.getId(), false);
				if (result) {
					setErrorMsg("Subscriber Name already exist");
					return;
				}
			} else {
				result = checkForDuplicate(view.getSubscriberNameTextField()
						.getText(), null, true);
				if (result) {
					setErrorMsg("Subscriber Name already exist");
					return;
				}
			}

			if (subscriber == null) {
				subscriber = new Subscriber();
			}

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

			if (MODIFY.equalsIgnoreCase(screenMode)) {
				SubscribeServiceImpl impl = new SubscribeServiceImpl();
				impl.modifySubscriber(subscriber);

			} else if (ADD.equalsIgnoreCase(screenMode)) {
				SubscribeServiceImpl impl = new SubscribeServiceImpl();
				impl.createSubscriber(subscriber);
			}

			RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);

		}

		private boolean validateMandatoryFields() {

			String errorMsgs = "";
			errorMsgs = checkEmpty(errorMsgs,
					"Subscriber Name is mandatory \n", view
							.getSubscriberNameTextField().getText());
			errorMsgs = checkEmpty(errorMsgs, "Pincode is mandatory", view
					.getSubscriberNameTextField().getText());
			if (errorMsgs != null && !errorMsgs.isEmpty()) {
				setErrorMsg(errorMsgs);
				return false;
			}
			try {
				Integer.parseInt(view.getPincodeTextField().getText());
			} catch (NumberFormatException e) {
				if (errorMsgs != null) {
					errorMsgs = errorMsgs
							.concat("Enter a numeric value for Pincode");
				} else {
					errorMsgs = "Enter a numeric value for Pincode";
				}
			}
			if (errorMsgs != null && !errorMsgs.isEmpty()) {
				setErrorMsg(errorMsgs);
				return false;
			}
			return true;
		}

		private String checkEmpty(String errorMsgs, String erroMsg, String text) {

			if (text == null || (text != null && text.isEmpty())) {
				errorMsgs = errorMsgs.concat(erroMsg);
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

			setErrorMsg("");
			setPopUpErrorMsg("", infoPopupView);
			Book bk = getBook(infoPopupView.getBookComboBox().getSelectedItem()
					.toString());
			if (isAdd != null && !isAdd) {
				if (subscribeInfo != null && subscribeInfo.getBook() != null
						&& subscribeInfo.getBook().getId() != bk.getId()) {

					if (subscribeInfos != null && !subscribeInfos.isEmpty()) {
						for (SubscribeInfo info : subscribeInfos) {
							if (info.getBook() != null
									&& info.getBook().getId() == bk.getId()) {

								setPopUpErrorMsg("Book is already subscribed",
										infoPopupView);
								return;
							}
						}
					}
				}
			} else if (isAdd != null && isAdd) {
				if (subscribeInfos != null && !subscribeInfos.isEmpty()) {
					for (SubscribeInfo info : subscribeInfos) {
						if (info.getBook() != null
								&& info.getBook().getId() == bk.getId()) {

							setPopUpErrorMsg("Book is already subscribed",
									infoPopupView);
							return;
						}
					}
				}

			}

			if (subscribeInfo == null) {
				subscribeInfo = new SubscribeInfo();
			}
			subscribeInfo.setBook(bk);
			subscribeInfo.setSubscribeDate(new Date(infoPopupView
					.getSubscribeDatePicker().getjTextField1().toString()));
			subscribeInfo.setNoOfYear(Integer.parseInt(infoPopupView
					.getNoOfYearComboBox().getSelectedItem().toString()));
			subscribeInfo.setExpiredDate(new Date(infoPopupView.getExpiryTxt()
					.getText().toString()));
			subscribeInfo.setSubscriber(subscriber);
			if (isAdd) {
				subscribeInfos.add(subscribeInfo);
			}
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
					subscribeInfos);
			view.getSubscribeInfoTable().setModel(dataModel);
			subscribeInfoPopup.dispose();
			subscribeInfo = null;

		}

	}

	class CancelAction implements ActionListener {

		SubscriberInfoPopupView infoPopupView;

		public CancelAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			subscribeInfoPopup.dispose();
			isAdd = null;
		}

	}

	class YearChangeEvent implements ItemListener {

		SubscriberInfoPopupView infoPopupView;

		public YearChangeEvent(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void itemStateChanged(ItemEvent e) {
			setPopUpErrorMsg("", infoPopupView);
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

	private boolean checkForDuplicate(String subscriberName,
			Integer subscriberId, boolean isCreate) {
		boolean isDuplicate = false;
		SubscribeServiceImpl subscribeServiceImpl = new SubscribeServiceImpl();
		isDuplicate = subscribeServiceImpl.checkDuplicate(subscriberName,
				subscriberId, isCreate);
		return isDuplicate;
	}

	private void setErrorMsg(String msg) {
		view.getErrorLabel().setText(msg);
	}

	private void setPopUpErrorMsg(String msg, SubscriberInfoPopupView view) {
		view.getErrorLabel().setText(msg);
	}
}
