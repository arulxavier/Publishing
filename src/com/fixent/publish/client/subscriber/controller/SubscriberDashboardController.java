package com.fixent.publish.client.subscriber.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.subscriber.view.SubscriberDashboardView;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.info.SearchInfo;
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
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);

		}

	}

	class SearchAction 
	implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			boolean status = true;
			SearchInfo info = new SearchInfo();
			
			String name = view.getNameTextField().getText();
			info.setName(name);
			
			String mobileNumber = view.getMobileNumberTextField().getText();
			
			if (mobileNumber != null && mobileNumber.length() > 0) {
				
				try {
					info.setMobileNumber(mobileNumber);
				} catch (Exception e2) {
					status = false;
					showPopup((RightPanel)view.getParent(), "Enter valid Mobile Number");
				}
			}
			
			String pincode = view.getPincodeTextField().getText();
			
			if (pincode != null && pincode.length() > 0) {
				
				try {
					info.setPincode(Integer.parseInt(pincode));
				} catch (Exception e2) {
					status = false;
					showPopup((RightPanel)view.getParent(), "Enter valid Pincode");
				}
			}

			
			
			if (status) {
				
				push("searchinfo", info);
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
