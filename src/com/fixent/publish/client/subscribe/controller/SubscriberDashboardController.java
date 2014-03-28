package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightSidePanel;
import com.fixent.publish.client.comon.RightPanel;
import com.fixent.publish.client.subscribe.view.SubscriberDashboardView;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberDashboardController extends BaseController {

	public SubscriberDashboardView view;

	public SubscriberDashboardController() {

		view = new SubscriberDashboardView();
		view.getAddButton().addActionListener(new AddAction());
		view.getSearchButton().addActionListener(new SearchAction());

	}

	class AddAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			push(ClientConstants.SCREEN_MODE, ClientConstants.ADD);
			/*RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);*/
			
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);

		}

	}

	class SearchAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

			Integer id = null;
			SubscribeServiceImpl subscribeServiceImpl = new SubscribeServiceImpl();
			List<Subscriber> subscribers;
			if (view.getSubscriberIdTextField().getText() != null
					&& !view.getSubscriberIdTextField().getText().isEmpty()
					&& !view.getSubscriberIdTextField().getText().trim()
							.isEmpty()) {
				try {
					id = Integer.parseInt(view.getSubscriberIdTextField()
							.getText());
				} catch (NumberFormatException e1) {
					setErrorMessages(view, "Please enter a numeric value");
				}

			}
			subscribers = subscribeServiceImpl.getSubscriber(id, view
					.getSubscriberNameTextField().getText());
			push("subscribersList", subscribers);
			if (id != null) {
				push("id", id);
			}
			if (view.getSubscriberNameTextField().getText() != null) {
				push("name", view.getSubscriberNameTextField().getText());
			}

			/*RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberListController().view,
					BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);*/
			
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberListController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}

}
