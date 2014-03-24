package com.fixent.publish.server.service.impl;

import java.io.File;

public class PDFOpen {
	
	public static void main(String[] args) {
		 
		  try {
	 
			if ((new File("C:\\cbeyond\\DeliveryReports.pdf")).exists()) {
	 
				Process p = Runtime
				   .getRuntime()
				   .exec("rundll32 url.dll,FileProtocolHandler C:\\cbeyond\\DeliveryReports.pdf");
				p.waitFor();
	 
			} else {
	 
				System.out.println("File is not exists");
	 
			}
	 
			System.out.println("Done");
	 
	  	  } catch (Exception ex) {
			ex.printStackTrace();
		  }
	 
		}

}
