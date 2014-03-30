package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.subscribe.view.SubscriberDashboardView;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class SubscriberDashboardController 
extends BaseController {

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

			boolean status = false;
			Integer id = null;
			List<Subscriber> subscribers;
			int pincode = 0;
			
			SubscribeServiceImpl subscribeServiceImpl = new SubscribeServiceImpl();
			
			String subscriberId = view.getSubscriberIdTextField().getText();
			if (subscriberId != null && !subscriberId.isEmpty()	&& !subscriberId.trim().isEmpty()) {
				
				try {
					
					id = Integer.parseInt(subscriberId);
					status = true;
				} catch (NumberFormatException e1) {
					
					status = false;
					showPopup(view, "Please enter a Valid ID");
				}

			} else {
				status = true;
			}
			
			String pin = view.getPincodeTextField().getText();
			if (pin != null && !pin.isEmpty()	&& !pin.trim().isEmpty()) {
				
				try {
					
					pincode = Integer.parseInt(pin);
					status = true;
				} catch (NumberFormatException e1) {
					
					status = false;
					showPopup(view, "Please enter Valid Pincode");
				}

			} else {
				status = true;
			}
			
			String name =  view.getSubscriberNameTextField().getText();
			
			if (status) {
				
				subscribers = subscribeServiceImpl.getSubscriber(id, name, pincode);
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

}
