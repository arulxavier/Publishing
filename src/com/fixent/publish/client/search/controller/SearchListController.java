package com.fixent.publish.client.search.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.RightPanel;
import com.fixent.publish.client.search.view.NotificationView;
import com.fixent.publish.client.subscriber.controller.SubscriberController;
import com.fixent.publish.server.model.Subscription;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.model.info.SubscriptionInfo;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;
import com.fixent.publish.server.service.impl.SubscriptionServiceImpl;

public class SearchListController extends BaseController {

	public NotificationView view;
	List<Subscription> subscriptions;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

	public SearchListController() {

		view = new NotificationView();

		setView();

		view.getPrintButton().addActionListener(new PrintButton());
		view.getCancelButton().addActionListener(new CancelAction());
	}

	class CancelAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new SearchDashboardController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}

	class PrintButton implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

//			setErrorMsg("");
			if (subscriptions != null && subscriptions.size() > 0) {
				
				PrintUtil.createPDFForSubscriberInfo(subscriptions, true, "Notification.pdf");
			} else {
				setErrorMsg("There is No Record to Print");
			}
			/*if (view.getNotificationTable().getSelectedRow() >  0)
			{
				final int row = view.getNotificationTable().getSelectedRow();
				SubscribeInfo subscribeInfo = subscribeInfos.get(row);
				List<SubscribeInfo> infoList = new ArrayList<SubscribeInfo>();
				infoList.add(subscribeInfo);
			}*/
		}

	}

	Subscription getBook(int id) {

		for (Subscription subscriber : subscriptions) {

			if (subscriber.getId() == id) {
				return subscriber;
			}
		}
		return null;
	}

	public void setView() {
		
		
		SubscriptionServiceImpl impl = new SubscriptionServiceImpl();
		
		SubscriptionInfo subscriptionInfo = (SubscriptionInfo) pop("searchinfo");
		subscriptions = impl.getSubscriptions(subscriptionInfo);
		SubscriptionDataTable dataTable = new SubscriptionDataTable(subscriptions);
		view.getNotificationTable().setModel(dataTable);
		/*int[] columnsWidth = {
				50, 50, 100, 50, 500
		};
		int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = view.getNotificationTable().getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }*/
	}

	private void setErrorMsg(String msg) {
		
		RightPanel rightSidePanel = (RightPanel)view.getParent();
		JOptionPane.showMessageDialog(rightSidePanel.getParent(), msg);
//		setErrorMessages(view.getParent(), msg);
	}

}
