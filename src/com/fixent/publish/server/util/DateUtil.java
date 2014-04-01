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

}
