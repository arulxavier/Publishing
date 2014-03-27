package com.fixent.publish.client.notification.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.notification.view.NotificationView;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class DeliveryController 
extends BaseController {

	public NotificationView view;
	List<SubscribeInfo> subscribers;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

	public DeliveryController() {

		view = new NotificationView();
		view.getNotificationLabel().setText("Delivery Reports");

		setView();

		view.getPrintButton().addActionListener(new PrintButton(this.subscribers));
		view.getPrintAllButton().addActionListener(new PrintAllButton());
	}

	class PrintAllButton implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			NotificationUtil.createPDFForSubscriber(subscribers, false, "DeliveryReports.pdf");
		}

	}

	class PrintButton implements ActionListener {
		
		
		List<SubscribeInfo> subscribeInfos;
		public PrintButton(List<SubscribeInfo> subscribeInfos) {
			
			
			
			this.subscribeInfos = subscribeInfos;
		}

		
		public void actionPerformed(ActionEvent e) {

			setErrorMsg("");
			
			if (!this.subscribeInfos.isEmpty()) {
				
//				final int row = view.getNotificationTable().getSelectedRow();
//				SubscribeInfo subscribeInfo = subscribers.get(row);
				List<SubscribeInfo> infoList = new ArrayList<SubscribeInfo>();
				infoList.addAll(this.subscribeInfos);
				NotificationUtil.createPDFForSubscriber(infoList, true, "DeliveryReports.pdf");
			}
		}

	}

	SubscribeInfo getBook(int id) {

		for (SubscribeInfo subscriber : subscribers) {

			if (subscriber.getId() == id) {
				return subscriber;
			}
		}
		return null;
	}

	public void setView() {
		SubscribeServiceImpl serviceImpl = new SubscribeServiceImpl();
		subscribers = serviceImpl.getDeliverySubscribers();
		DeliveryDataTable dataTable = new DeliveryDataTable(subscribers);
		view.getNotificationTable().setModel(dataTable);
	}

	private void setErrorMsg(String msg) {
		setErrorMessages(view.getParent(), msg);
	}

}
