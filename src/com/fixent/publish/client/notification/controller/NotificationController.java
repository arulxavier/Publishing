package com.fixent.publish.client.notification.controller;

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
import com.fixent.publish.client.notification.view.NotificationView;
import com.fixent.publish.client.subscribe.controller.SubscriberController;
import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.fixent.publish.server.model.info.SearchInfo;
import com.fixent.publish.server.service.impl.SubscribeServiceImpl;

public class NotificationController extends BaseController {

	public NotificationView view;
	List<SubscribeInfo> subscribeInfos;
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

	public NotificationController() {

		view = new NotificationView();

		setView();

		view.getPrintButton().addActionListener(new PrintButton());
		view.getCancelButton().addActionListener(new CancelAction());
	}

	class CancelAction implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			RightPanel rightSidePanel = (RightPanel)view.getParent();
			rightSidePanel.removeAll();
			rightSidePanel.add(new NotificationDashboardController().view, BorderLayout.CENTER);
			rightSidePanel.repaint();
			rightSidePanel.revalidate();
			rightSidePanel.setVisible(true);
		}

	}

	class PrintButton implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {

//			setErrorMsg("");
			if (subscribeInfos != null && subscribeInfos.size() > 0) {
				
				NotificationUtil.createPDFForSubscriberInfo(subscribeInfos, true, "Notification.pdf");
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

	SubscribeInfo getBook(int id) {

		for (SubscribeInfo subscriber : subscribeInfos) {

			if (subscriber.getId() == id) {
				return subscriber;
			}
		}
		return null;
	}

	public void setView() {
		
		
		SubscribeServiceImpl impl = new SubscribeServiceImpl();
		
		SearchInfo searchInfo = (SearchInfo)pop("searchinfo");
		subscribeInfos = impl.searchSubscribeInfo(searchInfo);
		DeliveryDataTable dataTable = new DeliveryDataTable(subscribeInfos);
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
