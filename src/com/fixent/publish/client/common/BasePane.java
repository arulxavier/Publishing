package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class BasePane 
extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasePane() {
		
		setName("BasePane");
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		addComponents();
	}

	private void addComponents() {
		
		add(new BaseLayout(), BorderLayout.CENTER);
	}

}
