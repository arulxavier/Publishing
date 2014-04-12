package com.fixent.publish.client.search.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.search.view.NotificationDashboardView;
import com.fixent.publish.client.subscriber.controller.SubscriberDashboardController;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.model.info.SubscriptionInfo;
import com.fixent.publish.server.service.impl.NotificationServiceImpl;
import com.fixent.publish.server.util.DateUtil;

public class SearchDashboardController 
extends BaseController {

	public NotificationDashboardView view;
	List<Subscription> subscribeInfos;
	
	public SearchDashboardController() {
		
		view = new NotificationDashboardView();
		
		view.getSearchButton().addActionListener(new SearchAction());
	}
	
	class SearchAction
	implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			SubscriptionInfo info = new SubscriptionInfo();
			
			String fd = view.getFromDatePicker().getjTextField1();
			
			if (fd != null && fd.length() > 0) {
				
				Date fromDate = DateUtil.getFirstDayOfTheMonth(new Date(fd));
				info.setFromDate(fromDate);
			}
			
			String ld = view.getToDatePicker().getjTextField1();
			
			if (ld != null && ld.length() > 0) {
				
				Date toDate = DateUtil.getLastDayOfTheMonth(new Date(ld));
				info.setToDate(toDate);
			}
			
			String bookName = view.getBookNameTextField().getText();
			info.setBookName(bookName);
			
			String subscriptionCode = view.getSubscriptionCodeTextField().getText();
			info.setSubscriptionCode(subscriptionCode);
			
			push("searchinfo", info);
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SearchListController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
			
		}
		
	}
	
	
}
