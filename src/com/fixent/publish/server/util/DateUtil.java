package com.fixent.publish.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date getLastDayOfTheMonth(Date date) {

		Date today = new Date();  

		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  

		calendar.add(Calendar.MONTH, 1);  
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.add(Calendar.DATE, -1);  

		Date lastDayOfMonth = calendar.getTime();  

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		return lastDayOfMonth;
	}
	
	public static Date getFirstDayOfTheMonth(Date date) {
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);  
		
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		
		Date date = new Date("20-Jun-2014");
		
		System.err.println(DateUtil.getLastDayOfTheMonth(date));
		System.out.println(DateUtil.getFirstDayOfTheMonth(date));
	}
	
	public static Date getMonthEnddateFromExpiryMonthAndYear(String expirydate) {
		
		Calendar calendar = null;
		
		if (expirydate != null && expirydate.length() > 0) {
			
			calendar = Calendar.getInstance();
			
			String[] expiryDateArray = expirydate.split("-");
			calendar.set(Calendar.MONTH, getMonth(expiryDateArray[0]));
			calendar.set(Calendar.YEAR, Integer.parseInt(expiryDateArray[1]));
			calendar.setTime(getLastDayOfTheMonth(calendar.getTime()));
		}		
		return calendar != null ? calendar.getTime() : null;
	}
	
	public static int getMonth(String month) {
		
		int monthValue = 0;
		if (month != null) {
			
			if (month.equalsIgnoreCase("jan"))
				monthValue =  0;
			else if (month.equalsIgnoreCase("feb"))
				monthValue =  1;
			else if (month.equalsIgnoreCase("mar"))
				monthValue =  2;
			else if (month.equalsIgnoreCase("apr"))
				monthValue =  3;
			else if (month.equalsIgnoreCase("may"))
				monthValue =  4;
			else if (month.equalsIgnoreCase("jun"))
				monthValue =  5;
			else if (month.equalsIgnoreCase("jul"))
				monthValue =  6;
			else if (month.equalsIgnoreCase("aug"))
				monthValue =  7;
			else if (month.equalsIgnoreCase("sep"))
				monthValue =  8;
			else if (month.equalsIgnoreCase("oct"))
				monthValue =  9;
			else if (month.equalsIgnoreCase("nov"))
				monthValue =  10;
			else if (month.equalsIgnoreCase("dec"))
				monthValue =  11;		
		}
		return monthValue;
	}

}
