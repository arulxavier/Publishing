package com.fixent.publish.client.publishedition.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.fixent.publish.client.common.BaseController;
import com.fixent.publish.client.common.ClientConstants;
import com.fixent.publish.client.common.RightSidePanel;
import com.fixent.publish.client.comon.RightPanel;
import com.fixent.publish.client.publishedition.view.PublishEditionBookView;
import com.fixent.publish.client.subscribe.controller.SubscriberDashboardController;
import com.fixent.publish.server.model.Book;
import com.fixent.publish.server.service.impl.BookServiceImpl;

public class PublishEditionController 
extends BaseController {

	public PublishEditionBookView view;
	List<Book> books;
	
	public PublishEditionController() {
		
		view = new PublishEditionBookView();
		
		setView();
		
		view.getSendEditionButton().addActionListener(new SendEditionAction());
	}
	
	private void setView() {
		
		BookServiceImpl bookService = new BookServiceImpl();
		books = bookService.getBooks();
		BookListDataTable dataTable = new BookListDataTable(books);
		view.getBookListTable().setModel(dataTable);
		
	}

	class SendEditionAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (view.getBookListTable().getSelectedRow() >=  0)
			{
				final int row = view.getBookListTable().getSelectedRow();
				Book book = books.get(row);
				
				push("book", book);

			/*	RightSidePanel rightSidePanel = (RightSidePanel) view.getParent();
				rightSidePanel.removeAll();
				rightSidePanel.add(new SendEditionController().view, BorderLayout.CENTER);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				rightSidePanel.setVisible(true);*/
				
				RightPanel rightSidePanel = (RightPanel)view.getParent();
				rightSidePanel.removeAll();
				rightSidePanel.add(new SendEditionController().view, BorderLayout.CENTER);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				rightSidePanel.setVisible(true);
				
				
			}
			
		}
		
	}
	
	

}
