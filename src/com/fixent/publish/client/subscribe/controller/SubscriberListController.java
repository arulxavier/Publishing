package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightSidePanel;
import com.fixent.publish.client.subscribe.view.SubscriberListView;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberListController extends BaseController {

	SubscriberListView view;
	List<Subscriber> subscribers;
	int id;
	String name;

	public SubscriberListController() {

		view = new SubscriberListView();
		view.getViewButton().addActionListener(new ViewAction());
		view.getModifyButton().addActionListener(new ModifyAction());
		view.getDeleteButton().addActionListener(new DeletAction());
		view.getCancelBtn().addActionListener(new MainCancelAction());
		subscribers = (List<Subscriber>) pop("subscribersList");
		Object idObject = pop("id");
		Object nameObject = pop("name");
		if (idObject != null) {
			id = (Integer) idObject;
			push("id", id);
		}
		if (nameObject != null) {
			name = (String) nameObject;
			push("name", name);
		}
		setView();
	}

	private void setView() {

		SubscriberListDataTable model = new SubscriberListDataTable(subscribers);
		view.getSubscriberListTable().setModel(model);
	}

	class ViewAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();
			System.out.println(subscribers.get(row).getId());

			push("subscriber", subscribers.get(row));
			push(ClientConstants.SCREEN_MODE, ClientConstants.VIEW);

			RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}
	}

	class ModifyAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();

			if (row >= 0) {
				push("subscriber", subscribers.get(row));
				push(ClientConstants.SCREEN_MODE, ClientConstants.MODIFY);

				RightSidePanel rightSidePanel = (RightSidePanel) view
						.getParent();
				rightSidePanel.removeAll();
				rightSidePanel.add(new SubscriberController().view,
						BorderLayout.CENTER);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				rightSidePanel.setVisible(true);
			}
		}
	}

	class DeletAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int row = view.getSubscriberListTable().getSelectedRow();

			if (row >= 0) {
				SubscribeServiceImpl serviceImpl = new SubscribeServiceImpl();
				boolean result = serviceImpl.deleteSubscriber(subscribers
						.get(row));
				SubscribeServiceImpl subscribeServiceImpl = new SubscribeServiceImpl();
				subscribers = subscribeServiceImpl.getSubscriber(id, name);
				setView();
			}

		}
	}

	class MainCancelAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberDashboardController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}
}
