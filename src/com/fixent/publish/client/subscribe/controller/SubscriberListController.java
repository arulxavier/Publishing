package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.notification.controller.NotificationUtil;
import com.fixent.publish.client.subscribe.view.SubscriberListView;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberListController extends BaseController {

	SubscriberListView view;
	List<Subscriber> subscribers;
	SearchInfo info;

	public SubscriberListController() {

		if (info == null) {
			info = (SearchInfo) pop("searchinfo");
		}
		
		view = new SubscriberListView();
		
		view.getViewButton().addActionListener(new ViewAction());
		view.getModifyButton().addActionListener(new ModifyAction());
		view.getDeleteButton().addActionListener(new DeletAction());
		view.getCancelBtn().addActionListener(new MainCancelAction());
		view.getPrintButton().addActionListener(new PrintAction());
		
		setView();
	}

	private void setView() {

		SubscribeServiceImpl impl = new SubscribeServiceImpl();
		subscribers = impl.searchSubscribers(info);
		SubscriberListDataTable model = new SubscriberListDataTable(subscribers);
		view.getSubscriberListTable().setModel(model);
	}
	
	class PrintAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			NotificationUtil.createPDFForSubscriber(subscribers, "SubscriberAddress.pdf");
			
		}
		
	}

	class ViewAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();
			System.out.println(subscribers.get(row).getId());

			push("subscriber", subscribers.get(row));
			push(ClientConstants.SCREEN_MODE, ClientConstants.VIEW);

			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}
	}

	class ModifyAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();

			if (row >= 0) {
				
				push("subscriber", subscribers.get(row));
				push(ClientConstants.SCREEN_MODE, ClientConstants.MODIFY);

				RightPanel rightSidePanel = (RightPanel)view.getParent();
				rightSidePanel.removeAll();
				rightSidePanel.add(new SubscriberController().view, BorderLayout.CENTER);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				rightSidePanel.setVisible(true);
				
				
			}
		}
	}

	class DeletAction 
	implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();

			if (row >= 0) {
				
				SubscribeServiceImpl serviceImpl = new SubscribeServiceImpl();
				boolean result = serviceImpl.deleteSubscriber(subscribers
						.get(row));
				setView();
			}

		}
	}

	class MainCancelAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}
}
