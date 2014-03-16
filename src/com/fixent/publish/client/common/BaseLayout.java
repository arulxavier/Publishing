package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.fixent.component.accordion.LeftSidePanel;

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
		
		LeftSidePanel leftSidePanel = new LeftSidePanel();
		leftSidePanel.setRootMenu(menu.getRootMenu());
		leftSidePanel.setSubMenuMap(menu.getSubMenuMap());
		AccordionEventHandler accordionEventHandler = new AccordionEventHandler(leftSidePanel);
		leftSidePanel.setAccordion(accordionEventHandler);
		add(leftSidePanel, BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.getViewport().add(new RightSidePanel());
		scrollPane.setWheelScrollingEnabled(true);
		scrollPane.setBorder(new javax.swing.border.LineBorder(new Color(61,86,109), 1, true));
		scrollPane.setForeground(Color.WHITE);
		
		add(scrollPane, BorderLayout.CENTER);
		add(new HeadePanel(), BorderLayout.NORTH);
		add(new FooterPanel(), BorderLayout.SOUTH);
		
		
	}


}
