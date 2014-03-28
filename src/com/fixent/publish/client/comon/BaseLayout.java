package com.fixent.publish.client.comon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Menu;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BaseLayout 
extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseLayout() {
		
		setName("BaseLayout");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(new javax.swing.border.LineBorder(Color.WHITE, 0, true));
		addComponents();
	}
	
	private void addComponents() {
		
		Menu menu = new Menu();
		
		LeftPanelController leftPanelController = new LeftPanelController();
		add(leftPanelController.leftPanel, BorderLayout.WEST);
		add(new HeaderPanel(), BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.getViewport().add(new RightPanel());
		scrollPane.setWheelScrollingEnabled(true);
		scrollPane.setBorder(new javax.swing.border.LineBorder(new Color(61,86,109), 1, true));
		scrollPane.setForeground(Color.WHITE);
		
		add(scrollPane, BorderLayout.CENTER);
		/*add(new FooterPanel(), BorderLayout.SOUTH);*/
		
		
	}


}
