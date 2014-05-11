package com.fixent.publish.client.subscriber.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.subscriber.view.SubscriberView;
import com.fixent.publish.client.subscriber.view.SubscriptionPopupView;
import com.fixent.publish.server.model.Address;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SubscriptionInfo;
import com.fixent.publish.server.service.impl.BookServiceImpl;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;
import com.fixent.publish.server.service.impl.SubscriptionServiceImpl;

public class SubscriberController extends BaseController {

	SubscriberView view;
	List<Book> books;
	JDialog subscribeInfoPopup;
	List<Subscription> subscriptions = new ArrayList<Subscription>();
	Subscription subscription;
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
				subscriptions);
		view.getSubscriptionTable().setModel(dataModel);
		if (ADD.equalsIgnoreCase(screenMode)) {

			view.getStateTextField().setText("Tamil Nadu");
			view.getCountryTextField().setText("India");
		} else if (MODIFY.equalsIgnoreCase(screenMode)) {

			subscriber = (Subscriber) OBJECT_MAP.get("subscriber");
			setView();
		} else if (VIEW.equalsIgnoreCase(screenMode)) {

			subscriber = (Subscriber) OBJECT_MAP.get("subscriber");
			setView();
			setViewMode();
		} 

		view.getAddButton().addActionListener(new AddAction());
		view.getSubscriptionTable().addMouseListener(new SubscriptionTableClickAction());
		view.getSaveButton().addActionListener(new SaveAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
		view.getCancelBtn().addActionListener(new MainCancelAction());
	}

	private void setViewMode() {
		
		view.getSubscriberNameTextField().setEditable(false);
		view.getMobileNumberTextField().setEditable(false);
		view.getStreet1TextField().setEditable(false);
		view.getStreet2TextField().setEditable(false);
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
			view.getStreet1TextField().setText(subscriber.getAddress().getStreet1());
			view.getStreet2TextField().setText(subscriber.getAddress().getStreet2());
			view.getCityTextField().setText(subscriber.getAddress().getCity());
			view.getStateTextField().setText(subscriber.getAddress().getState());
			view.getCountryTextField().setText(subscriber.getAddress().getCountry());
			view.getPincodeTextField().setText(
					String.valueOf(subscriber.getAddress().getPincode()));
			if (subscriber.getSubscriptions() != null) {
				subscriptions = new ArrayList<Subscription>(
						subscriber.getSubscriptions());
				SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
						subscriptions);
				view.getSubscriptionTable().setModel(dataModel);
			}
		}

	}
	
	class GroupCodeFocusEvent
	implements FocusListener {
		
		SubscriptionPopupView subscriptionPopupView;
		
		public GroupCodeFocusEvent(SubscriptionPopupView subscriptionPopupView) {
			
			this.subscriptionPopupView = subscriptionPopupView;
		}

		public void focusGained(FocusEvent e) {
		}

		public void focusLost(FocusEvent e) {
			
			String bookName = subscriptionPopupView.getBookComboBox().getSelectedItem().toString();
			
			if (bookName != null && bookName.length() > 0 && !bookName.equals("Select One")) {
				
				SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
				subscriptionInfo.setBookName(bookName);
				subscriptionInfo.setSubscriptionGroup(subscriptionPopupView.getGroupCodeTextField().getText());
				
				SubscriptionServiceImpl impl = new SubscriptionServiceImpl();
				String code = impl.getSubscriptionCode(subscriptionInfo);
				subscriptionPopupView.getCodeTextField().setText(code);
			} else {
				
				subscriptionPopupView.getErrorLabel().setText("Select Book to Generate Group Code");
			}
		}
		
	}

	class SubscriptionTableClickAction extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setErrorMsg("");
			
			if (e.getClickCount() == 2) {
				
				final int row = view.getSubscriptionTable().getSelectedRow();
				subscription = subscriptions.get(row);
				
				SubscriptionPopupView subscriptionPopupView = new SubscriptionPopupView();
				setPopUpErrorMsg("", subscriptionPopupView);
				subscriptionPopupView.getSaveButton().addActionListener(new SavePopupAction(subscriptionPopupView));
				subscriptionPopupView.getCancelButton().addActionListener(new CancelAction(subscriptionPopupView));
				subscriptionPopupView.getNoOfYearComboBox().addItemListener(new YearChangeEvent(subscriptionPopupView));
				subscriptionPopupView.getSubscribeDatePicker().getDateField().addCaretListener(new SubscribedAction(subscriptionPopupView));
				subscriptionPopupView.getGroupCodeTextField().addFocusListener(new GroupCodeFocusEvent(subscriptionPopupView));
				subscriptionPopupView.getBookComboBox().addItemListener(new BookSelectionEvent(subscriptionPopupView));
				
				DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
				boxModel.addElement("Select One");

				for (Book book : books) {

					boxModel.addElement(book.getName());
				}

				if (VIEW.equalsIgnoreCase(screenMode)) {
					
					subscriptionPopupView.getSaveButton().setEnabled(false);
					subscriptionPopupView.getBookComboBox().setEnabled(false);
					subscriptionPopupView.getSubscribeDatePicker().setEditable(false);
					subscriptionPopupView.getNoOfYearComboBox().setEnabled(false);
					subscriptionPopupView.getGroupCodeTextField().setEditable(false);
					subscriptionPopupView.getCodeTextField().setEditable(false);
				} else {
					subscriptionPopupView.getExpiryTxt().setEditable(true);
					subscriptionPopupView.getExpiryTxt().setEnabled(true);
				}
				subscriptionPopupView.getBookComboBox().setModel(boxModel);

				subscriptionPopupView.getBookComboBox().setSelectedItem(subscription.getBook().getName());
				subscriptionPopupView.getNoOfYearComboBox().setSelectedItem(String.valueOf(subscription.getSubscriptionYear()));
				subscriptionPopupView.getSubscribeDatePicker().setDateTextField(subscription.getSubscriptionDate());
				subscriptionPopupView.getExpiryTxt().setText(DATE_FORMAT.format(subscription.getSubscriptionExpiredDate()).toString());
				subscriptionPopupView.getGroupCodeTextField().setText(subscription.getSubscriptionGroup());
				subscriptionPopupView.getCodeTextField().setText(subscription.getSubscriptionCode());
								

				subscribeInfoPopup = new JDialog();
				subscribeInfoPopup.add(subscriptionPopupView);
				subscribeInfoPopup.setSize(670, 270);
				subscribeInfoPopup.setResizable(false);
				subscribeInfoPopup.setLocationRelativeTo(null);
				subscribeInfoPopup.setVisible(true);
				isAdd = false;
			}
		}
	}

	class SubscribedAction implements CaretListener {
		SubscriptionPopupView infoPopupView;

		public SubscribedAction(SubscriptionPopupView infoPopupView) {
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

			SubscriptionPopupView subscriptionPopupView = new SubscriptionPopupView();
			setErrorMsg("");
			setPopUpErrorMsg("", subscriptionPopupView);
			
			subscriptionPopupView.getSaveButton().addActionListener(new SavePopupAction(subscriptionPopupView));
			subscriptionPopupView.getCancelButton().addActionListener(new CancelAction(subscriptionPopupView));
			subscriptionPopupView.getNoOfYearComboBox().addItemListener(new YearChangeEvent(subscriptionPopupView));
			subscriptionPopupView.getSubscribeDatePicker().getDateField().addCaretListener(new SubscribedAction(subscriptionPopupView));
			subscriptionPopupView.getGroupCodeTextField().addFocusListener(new GroupCodeFocusEvent(subscriptionPopupView));
			subscriptionPopupView.getBookComboBox().addItemListener(new BookSelectionEvent(subscriptionPopupView));
			
			DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
			boxModel.addElement("Select One");
			for (Book book : books) {

				boxModel.addElement(book.getName());
			}
			subscriptionPopupView.getBookComboBox().setModel(boxModel);
			
			subscriptionPopupView.getExpiryTxt().setEditable(true);
			subscriptionPopupView.getExpiryTxt().setEnabled(true);

			subscribeInfoPopup = new JDialog();
			subscribeInfoPopup.add(subscriptionPopupView);
			subscribeInfoPopup.setSize(670, 270);
			subscribeInfoPopup.setResizable(false);
			subscribeInfoPopup.setLocationRelativeTo(null);
			subscribeInfoPopup.setVisible(true);
			isAdd = true;
		}
	}
	
	class BookSelectionEvent
	implements ItemListener {

		SubscriptionPopupView subscriptionPopupView;
		
		public BookSelectionEvent(SubscriptionPopupView subscriptionPopupView) {
			
			this.subscriptionPopupView = subscriptionPopupView;
		}
		public void itemStateChanged(ItemEvent e) {
			
			String groupCode = subscriptionPopupView.getGroupCodeTextField().getText();
			
			if (groupCode != null && groupCode.length() > 0) {
				
				SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
				subscriptionInfo.setBookName(subscriptionPopupView.getBookComboBox().getSelectedItem().toString());
				subscriptionInfo.setSubscriptionGroup(groupCode);
				
				SubscriptionServiceImpl impl = new SubscriptionServiceImpl();
				String code = impl.getSubscriptionCode(subscriptionInfo);
				subscriptionPopupView.getCodeTextField().setText(code);
			}
			subscriptionPopupView.getErrorLabel().setText("");
		}
		
	}

	class MainCancelAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberListController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}

	class DeleteAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			final int row = view.getSubscriptionTable().getSelectedRow();
			subscriptions.remove(row);
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(
					subscriptions);
			view.getSubscriptionTable().setModel(dataModel);
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

			Address address;
			if (subscriber == null) {
				subscriber = new Subscriber();
				address = new Address();
			} else {
				address = subscriber.getAddress();
			}

			subscriber.setName(view.getSubscriberNameTextField().getText());
			subscriber.setMobileNumber(view.getMobileNumberTextField().getText());
			
			address.setStreet1(view.getStreet1TextField().getText());
			address.setStreet2(view.getStreet2TextField().getText());
			address.setCity(view.getCityTextField().getText());
			address.setState(view.getStateTextField().getText());
			address.setCountry(view.getCountryTextField().getText());
			address.setPincode(Integer.parseInt(view.getPincodeTextField()
					.getText()));
			subscriber.setAddress(address);

			Set<Subscription> infoSet = new HashSet<Subscription>();
			infoSet.addAll(subscriptions);
			subscriber.setSubscriptions(infoSet);

			if (MODIFY.equalsIgnoreCase(screenMode)) {
				SubscribeServiceImpl impl = new SubscribeServiceImpl();
				impl.modifySubscriber(subscriber);

			} else if (ADD.equalsIgnoreCase(screenMode)) {
				SubscribeServiceImpl impl = new SubscribeServiceImpl();
				impl.createSubscriber(subscriber);
			}

			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberListController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);

		}

		private boolean validateMandatoryFields() {

			String errorMsgs = "";
			
			errorMsgs = checkEmpty(errorMsgs, "Subscriber Name is mandatory \n", view.getSubscriberNameTextField().getText());
			errorMsgs = checkEmpty(errorMsgs, "Pincode is mandatory", view.getSubscriberNameTextField().getText());
			
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

		SubscriptionPopupView subscriptionPopupView;

		public SavePopupAction(SubscriptionPopupView infoPopupView) {
			this.subscriptionPopupView = infoPopupView;
		}

		@SuppressWarnings("deprecation")
		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			setPopUpErrorMsg("", subscriptionPopupView);
			String bookName = "";
			String subscriptionGroup = null;
			String subscriptionCode = null;
			Integer subscriptionNumber = 0;
			String subscriptionDate = null;
					
			if (subscription == null) {
				subscription = new Subscription();
			}
			
			bookName = subscriptionPopupView.getBookComboBox().getSelectedItem().toString();
			Book book = getBook(bookName);
			subscription.setBook(book);
			
			subscriptionDate = subscriptionPopupView.getSubscribeDatePicker().getjTextField1();
			if (subscriptionDate != null && subscriptionDate.length() > 0) {
				subscription.setSubscriptionDate(new Date(subscriptionDate));
			}
			subscriptionGroup = subscriptionPopupView.getGroupCodeTextField().getText();
			subscription.setSubscriptionGroup(subscriptionGroup);
			subscriptionCode = subscriptionPopupView.getCodeTextField().getText();
			subscription.setSubscriptionCode(subscriptionCode);
			subscriptionNumber = getSubscriptionNumber(subscription);
			subscription.setSubscriptionNumber(subscriptionNumber);
			String subscriptionYear = subscriptionPopupView.getNoOfYearComboBox().getSelectedItem().toString();
			if (subscriptionYear.equals("Select One")) {
				subscription.setSubscriptionYear(0);
			} else {
				subscription.setSubscriptionYear(Integer.parseInt(subscriptionYear));
			}
			String subscriptionExpiryDate = subscriptionPopupView.getExpiryTxt().getText();
			if (subscriptionExpiryDate != null && subscriptionExpiryDate.length() > 0) {
				subscription.setSubscriptionExpiredDate(new Date(subscriptionExpiryDate));
			}
			
			if (validateSubscription(subscriptionPopupView, subscription)) {
				
				if (isAdd != null && !isAdd) {
					
					if (subscription != null 
							&& subscription.getBook() != null
							&& subscription.getBook().getId() != book.getId()) {

						if (subscriptions != null && !subscriptions.isEmpty()) {
							
							for (Subscription info : subscriptions) {
								
								if (info.getBook() != null
										&& info.getBook().getId() == book.getId()) {

									setPopUpErrorMsg("Book is already subscribed",
											subscriptionPopupView);
									return;
								}
							}
						}
					}
				} else if (isAdd != null && isAdd) {
					
					if (subscriptions != null && !subscriptions.isEmpty()) {
						
						for (Subscription info : subscriptions) {
							if (info.getBook() != null
									&& info.getBook().getId() == book.getId()) {

								setPopUpErrorMsg("Book is already subscribed",
										subscriptionPopupView);
								return;
							}
						}
					}

				}
				if (isAdd) {
					subscriptions.add(subscription);
				}
				SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(subscriptions);
				view.getSubscriptionTable().setModel(dataModel);
				subscribeInfoPopup.dispose();
				subscription = null;
			}
		}

		private Integer getSubscriptionNumber(Subscription subscription) {
			
			int fromIndex = subscription.getSubscriptionGroup().length();
			int endIndex = subscription.getSubscriptionCode().length();
			String subscriptionNumber = subscription.getSubscriptionCode().substring(fromIndex, endIndex);
			return Integer.parseInt(subscriptionNumber);
		}
	}
	
	private boolean validateSubscription(SubscriptionPopupView subscriptionPopupView,	
										 Subscription subscription) {
		
		StringBuffer buffer = new StringBuffer();
		Boolean status = false;
		if (subscription != null) {
			
			if (subscription.getBook() == null) {
				buffer.append(" Book is Mandatory : \n");
			}
			if (subscription.getSubscriptionGroup() == null || subscription.getSubscriptionGroup().length() == 0) {
				buffer.append(" Group Code is Mandatory : \n");
			}
			if (subscription.getSubscriptionCode() == null || subscription.getSubscriptionCode().length() == 0) {
				buffer.append(" Subscription Code is Mandatory : \n");
			}
			if (subscription.getSubscriptionExpiredDate() == null) {
				buffer.append(" Expiry Date is Mandatory : \n");
			}
			if (subscription.getSubscriptionYear() == 0) {
				buffer.append(" Subscription Year is Mandatory : \n");
			}
		}
		
		if (buffer.length() > 0) {
			
			status = false;
			setPopUpErrorMsg(buffer.toString(), subscriptionPopupView);
		} else {
			status = true;
		}
		return status;
	}

	class CancelAction implements ActionListener {

		SubscriptionPopupView infoPopupView;

		public CancelAction(SubscriptionPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			subscribeInfoPopup.dispose();
			isAdd = null;
		}

	}

	class YearChangeEvent implements ItemListener {

		SubscriptionPopupView infoPopupView;

		public YearChangeEvent(SubscriptionPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		
		public void itemStateChanged(ItemEvent e) {
			setPopUpErrorMsg("", infoPopupView);
			setExpiryDateValue(infoPopupView);
		}

	}

	@SuppressWarnings("deprecation")
	public void setExpiryDateValue(SubscriptionPopupView infoPopupView) {

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

	private void setPopUpErrorMsg(String msg, SubscriptionPopupView view) {
		view.getErrorLabel().setText(msg);
	}
}
