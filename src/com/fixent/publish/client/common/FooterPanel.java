package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class FooterPanel 
extends JPanel {
	
	FooterView footerView;
	JWindow errorPopup;
	ErrorMessageView errorMessageView = new ErrorMessageView();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FooterPanel() {
		
		footerView = new FooterView();
		
		footerView.getMoreLabel().addMouseListener(new MoreAction());
		
		setLayout(new BorderLayout());
		add(footerView);
	}
	
	class MoreAction
	implements MouseListener {

		
		public void mouseClicked(MouseEvent e) {
			
			errorPopup = new JWindow();
			errorPopup.add(errorMessageView);
			errorMessageView.getCloseButton().addActionListener(new CloseAction());
			errorPopup.setBounds(400, 420, 400, 250);
			errorPopup.setVisible(true);			
		}

		
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class CloseAction
	implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			errorPopup.dispose();
		}
		
	}

}
