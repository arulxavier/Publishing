package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class RightSidePanel 
extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RightSidePanel() {
		
		setLayout(new BorderLayout());
		setBorder(new javax.swing.border.LineBorder(new Color(61,86,109), 0, true));
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
	}

}
