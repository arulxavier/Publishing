package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class HeadePanel 
extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeadePanel() {
		setLayout(new BorderLayout());
		setBorder(new javax.swing.border.LineBorder(Color.WHITE, 0, true));
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		add(new HeaderView(), BorderLayout.CENTER);
	}

}
