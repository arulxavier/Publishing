package com.fixent.publish.client.subscribe.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.subscribe.view.SubscriberDashboardView;

public class SubscriberDashboardController {

	public SubscriberDashboardView view;
	
	public SubscriberDashboardController() {
		
		view = new SubscriberDashboardView();
		view.getAddButton().addActionListener(new AddAction());
		view.getSearchButton().addActionListener(new SearchAction());
		
	}
	
	class AddAction
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
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

		@Override
		public void actionPerformed(ActionEvent e) {
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SubscriberListController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}
		
	}
	
	
}
