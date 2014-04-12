//package com.fixent.publish.server.service.impl;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import com.fixent.publish.client.book.controller.PaymentSchedule;
//
//public class Main {
//
//	public static void main(String[] args) {
//		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//		Calendar yearStartDate = Calendar.getInstance();
//		yearStartDate.set(2013, 9, 1);
//
//		System.out.println(yearStartDate.getTime());
//		Calendar yearEndDate = Calendar.getInstance();
//		yearEndDate.set(2014, 9, 31);
//		System.out.println(yearEndDate.getTime());
//
//		Calendar monthStartDate = Calendar.getInstance();
//		monthStartDate.setTime(yearStartDate.getTime());
//
//		Calendar monthEndDate = Calendar.getInstance();
//
////		List<PaymentSchedule> paymentSchedules = new ArrayList<PaymentSchedule>();
//		int i = 1;
//
//		do {
//
////			PaymentSchedule paymentSchedule = new PaymentSchedule();
//			paymentSchedule.setId(i);
//			i++;
//			paymentSchedule.setFromDate(monthStartDate.getTime());
//
//			// int endDayOfCurrentMonth =
//			// monthStartDate.getMaximum(Calendar.DAY_OF_MONTH);
//			// monthEndDate.set(monthStartDate.get(Calendar.YEAR),
//			// monthStartDate.get(Calendar.MONTH), endDayOfCurrentMonth);
//			monthEndDate.setTime(monthStartDate.getTime());
//			monthEndDate.add(Calendar.MONTH, 1);
//			monthEndDate.set(Calendar.DAY_OF_MONTH, 1);
//			monthEndDate.add(Calendar.DATE, -1);
//
//			paymentSchedule.setToDate(monthEndDate.getTime());
//			paymentSchedules.add(paymentSchedule);
//			monthStartDate.add(Calendar.MONTH, 1);
//		} while (0 <= yearEndDate.compareTo(monthEndDate));
//
//		for (PaymentSchedule paymentSchedule : paymentSchedules) {
//
//			System.out.println("ID :" + paymentSchedule.getId()
//					+ " From Date :"
//					+ format.format(paymentSchedule.getFromDate()) + "  "
//					+ "To Date :" + format.format(paymentSchedule.getToDate()));
//		}
//		System.out.println("End");
//	}
//}
