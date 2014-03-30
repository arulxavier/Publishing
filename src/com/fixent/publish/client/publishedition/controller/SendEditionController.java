package com.fixent.publish.client.publishedition.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.notification.controller.NotificationUtil;
import com.fixent.publish.client.publishedition.view.SendEditionView;
import com.fixent.publish.client.subscribe.controller.SubscriberController;
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
		
		view.getBookNameTextField().setText(book.getName().trim());
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
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new PublishEditionController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
			
		}
		
	}

}
