package com.fixent.publish.client.common;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class BaseController {

	public static Map<String, Object> OBJECT_MAP = new HashMap<String, Object>();

	public static void push(String key, Object value) {
		OBJECT_MAP.put(key, value);
	}

	public static Object pop(String key) {
		return OBJECT_MAP.remove(key);
	}

	public void setErrorMessages(Container jPanel, String msg) {

		RightSidePanel rightSidePanel = (RightSidePanel) jPanel;
		JViewport jViewport = (JViewport) rightSidePanel.getParent();
		JScrollPane jScrollPane = (JScrollPane) jViewport.getParent();
		BaseLayout baseLayout = (BaseLayout) jScrollPane.getParent();
		String message = "";
		for (Component component : baseLayout.getComponents()) {

			if (component instanceof FooterPanel) {

				FooterPanel footerPanel = (FooterPanel) component;
				if (msg.isEmpty()) {
					footerPanel.footerView.getErrorLabel().setText("");
					footerPanel.footerView.getMoreLabel().setText("");
					footerPanel.errorMessageView.getMessageLabel().setText("");
				} else {
					footerPanel.footerView.getErrorLabel().setText(
							"Error occured click more to view detail msg");
					footerPanel.footerView.getMoreLabel().setText("more.....");
					if (footerPanel.errorMessageView.getMessageLabel()
							.getText().length() > 0) {

						message = footerPanel.errorMessageView
								.getMessageLabel().getText();
						message = message + "\n" + msg;
					} else {
						message = msg;
					}
					footerPanel.errorMessageView.getMessageLabel().setText(
							message);
				}

			}
		}
	}

	public DefaultComboBoxModel getYears() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("Select One");
		for (int i = 1900; i <= 2050; i++) {
			model.addElement(String.valueOf(i));
		}
		return model;
	}
	
	
}
