package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.subscribe.view.SubscriberInfoPopupView;
import com.fixent.publish.client.subscribe.view.SubscriberView;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.BookServiceImpl;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberController {
	
	SubscriberView view;
	List<Book> books;
	JDialog subscribeInfoPopup;
	List<SubscribeInfo> subscribeInfos = new ArrayList<SubscribeInfo>();
	
	public SubscriberController() {
		
		view = new SubscriberView();
		
		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		books = bookServiceImpl.getBooks();
		view.getAddButton().addActionListener(new AddAction());
		view.getSaveButton().addActionListener(new SaveAction());
		view.getDeleteButton().addActionListener(new DeleteAction());
		
	}
	
	class AddAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			SubscriberInfoPopupView infoPopupView = new SubscriberInfoPopupView();
			
			infoPopupView.getSaveButton().addActionListener(new SavePopupAction(infoPopupView));
			infoPopupView.getCancelButton().addActionListener(new CancelAction(infoPopupView));
			infoPopupView.getNoOfYearComboBox().addItemListener(new YearChangeEvent(infoPopupView));
			
			DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
			
			boxModel.addElement("Select One");
			
			for (Book book : books) {
				
				boxModel.addElement(book.getName());
			}
			
			infoPopupView.getBookComboBox().setModel(boxModel);

			subscribeInfoPopup = new JDialog();
			subscribeInfoPopup.add(infoPopupView);
			subscribeInfoPopup.setSize(600, 400);
			subscribeInfoPopup.setResizable(false);			
			subscribeInfoPopup.setLocationRelativeTo(null);
			subscribeInfoPopup.setVisible(true);
			
		}
		
		
	}
	
	class DeleteAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class SaveAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Subscriber subscriber = new Subscriber();
			subscriber.setName(view.getSubscriberNameTextField().getText());
			subscriber.setMobileNumber(view.getMobileNumberTextField().getText());
			
			subscriber.setStreet(view.getStreetTextField().getText());
			subscriber.setCity(view.getCityTextField().getText());
			subscriber.setState(view.getStateTextField().getText());
			subscriber.setCountry(view.getCountryTextField().getText());
			subscriber.setPincode(Integer.parseInt(view.getPincodeTextField().getText()));
			
			Set<SubscribeInfo> infoSet = new HashSet<SubscribeInfo>();
			infoSet.addAll(subscribeInfos);
			subscriber.setSubscribeInfos(infoSet);
			
			SubscribeServiceImpl impl = new SubscribeServiceImpl();
			impl.createSubscriber(subscriber);
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
			
		}
		
	}
	
	class SavePopupAction
	implements ActionListener {
		
		SubscriberInfoPopupView infoPopupView;
		
		public SavePopupAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			SubscribeInfo info = new SubscribeInfo();
			info.setBook(getBook(infoPopupView.getBookComboBox().getSelectedItem().toString()));
			info.setSubscribeDate(new Date(infoPopupView.getSubscribeDatePicker().getjTextField1().toString()));
			info.setNoOfYear(Integer.parseInt(infoPopupView.getNoOfYearComboBox().getSelectedItem().toString()));
			info.setExpiredDate(new Date(infoPopupView.getExpiredDatePicker().getjTextField1().toString()));
			subscribeInfos.add(info);
			SubscribeInfoDataTable dataModel = new SubscribeInfoDataTable(subscribeInfos);
			view.getSubscribeInfoTable().setModel(dataModel);
			subscribeInfoPopup.dispose();
			
		}
		
	}
	
	class CancelAction
	implements ActionListener {
		
		SubscriberInfoPopupView infoPopupView;
		
		public CancelAction(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			subscribeInfoPopup.dispose();
		}
		
	}
	
	class YearChangeEvent
	implements  ItemListener {

		
		SubscriberInfoPopupView infoPopupView;
		
		public YearChangeEvent(SubscriberInfoPopupView infoPopupView) {
			this.infoPopupView = infoPopupView;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			int noOfYear = Integer.parseInt(infoPopupView.getNoOfYearComboBox().getSelectedItem().toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(infoPopupView.getSubscribeDatePicker().getjTextField1()));
			calendar.add(Calendar.YEAR, noOfYear);
			infoPopupView.getExpiredDatePicker().setDateTextField(calendar.getTime().toString());
			
		}
		
	}
	
	
	Book getBook(String bookName) {
		
		for (Book book : books) {
				
			if (book.getName().equalsIgnoreCase(bookName)) {
				return book;
			}
		}
		return null;
	}
	

}
