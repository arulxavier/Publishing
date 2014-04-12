package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import com.fixent.publish.client.book.controller.BookController;
import com.fixent.publish.client.publishedition.controller.PublishEditionController;
import com.fixent.publish.client.search.controller.SearchListController;
import com.fixent.publish.client.search.controller.SearchDashboardController;
import com.fixent.publish.client.subscriber.controller.SubscriberDashboardController;

public class LeftPanelController {

	LeftPanel leftPanel;

	public LeftPanelController() {

		leftPanel = new LeftPanel();
		leftPanel.getBookButton().addActionListener(new BookAction());
		leftPanel.getSubscriberButton().addActionListener(new SubscriberAction());
		leftPanel.getPublishEditionButton().addActionListener(new PublishEditionAction());
		leftPanel.getNotificationButton().addActionListener(new NotificationAction());
	}

	class BookAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			navigateAction(e);
		}

	}

	class SubscriberAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			navigateAction(e);
		}

	}
	
	class PublishEditionAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			navigateAction(e);			
		}
	}
	
	class NotificationAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			navigateAction(e);				
		}
	}
	
	
	void navigateAction(ActionEvent e) {
		


//		System.out.println(leftPanel.getParent());

		for (Component component2 : leftPanel.getParent()
				.getComponents()) {

			if (component2 instanceof JScrollPane) {

				for (Component component : ((JScrollPane) component2)
						.getComponents()) {

					if (component instanceof JViewport) {

						RightPanel rightSidePanel = (RightPanel) ((JViewport) component)
								.getComponents()[0];
						rightSidePanel.removeAll();
						if (((JButton)e.getSource()).getText().equalsIgnoreCase(
								"Book")) {

							rightSidePanel
									.add(new BookController().view,
											BorderLayout.CENTER);
						} else if (((JButton)e.getSource()).getText().equalsIgnoreCase(
								"Subscription")) {

							rightSidePanel
									.add(new SubscriberDashboardController().view,
											BorderLayout.CENTER);
						}  else if (((JButton)e.getSource()).getText().equalsIgnoreCase(
								"Book Publish")) {

							rightSidePanel
									.add(new PublishEditionController().view,
											BorderLayout.CENTER);
						}  else if (((JButton)e.getSource()).getText().equalsIgnoreCase(
								"Search")) {

							rightSidePanel
									.add(new SearchDashboardController().view,
											BorderLayout.CENTER);
						}
						rightSidePanel.repaint();
						rightSidePanel.revalidate();
						rightSidePanel.setVisible(true);
					}
				}

			}
		}

	
	}

}
