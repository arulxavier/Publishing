package com.fixent.publish.client.notification.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.notification.view.NotificationDashboardView;
import com.fixent.publish.client.subscribe.controller.SubscriberDashboardController;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.service.impl.NotificationServiceImpl;
import com.fixent.publish.server.util.DateUtil;

public class NotificationDashboardController 
extends BaseController {

	public NotificationDashboardView view;
	List<SubscribeInfo> subscribeInfos;
	
	public NotificationDashboardController() {
		
		view = new NotificationDashboardView();
		
		view.getSearchButton().addActionListener(new SearchAction());
	}
	
	class SearchAction
	implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			SearchInfo info = new SearchInfo();
			
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
			
			push("searchinfo", info);
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new NotificationController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
			
		}
		
	}
	
	
}
