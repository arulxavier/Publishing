package com.fixent.publish.client.common;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class BaseFrame 
extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseFrame() {
		
		
		setName("BaseFrame");
//		setBounds(250, 50, 960, 650);
	    setSize(1000, 650);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		add(new BasePane(), BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
