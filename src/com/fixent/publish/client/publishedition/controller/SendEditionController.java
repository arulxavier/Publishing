package com.fixent.publish.client.publishedition.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.notification.controller.NotificationUtil;
import com.fixent.publish.client.publishedition.view.SendEditionView;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.model.Edition;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SendEditionController 
extends BaseController {
	
	SendEditionView view;
	Book book;
	List<SubscribeInfo> subscriberInfos;
	
	public SendEditionController() {
		
		book = (Book) pop("book");
		view = new SendEditionView();
		
		setView();
		
		view.getSendButton().addActionListener(new SendAction());
	}

	private void setView() {
		
		view.getBookNameTextField().setText(book.getName());
		view.getAuthorTextField().setText(book.getAuthor());
		view.getFrequencyTextField().setText(book.getFrequency());
		
		SubscribeServiceImpl subscribeServiceImpl = new SubscribeServiceImpl();
		this.subscriberInfos = subscribeServiceImpl.getSubscribersByBook(book);
		
		SubscriberDataTable dataTable = new SubscriberDataTable(subscriberInfos);
		view.getSubscriberListTable().setModel(dataTable);
		
	}
	
	class SendAction 
	implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			Edition edition = new Edition();
			edition.setEditionDate(new Date());
			edition.setName(view.getEditionTextField().getText());
			edition.setBook(book);
			SubscribeServiceImpl impl = new SubscribeServiceImpl();
			boolean status = impl.saveEdition(edition);
			if (status) {
				System.out.println("Success");
				List<SubscribeInfo> infoList = new ArrayList<SubscribeInfo>();
				infoList.addAll(subscriberInfos);
				NotificationUtil.createPDFForSubscriber(infoList, true, "DeliveryReports.pdf");
			}
			
		}
		
	}

}