package com.fixent.publish.client.notification.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.fixent.publish.server.model.SubscribeInfo;
import com.fixent.publish.server.model.Subscriber;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class NotificationUtil {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);

	public static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public static void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor("First Chapter", catFont);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Subcategory 1", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Hello"));

		subPara = new Paragraph("Subcategory 2", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Paragraph 1"));
		subCatPart.add(new Paragraph("Paragraph 2"));
		subCatPart.add(new Paragraph("Paragraph 3"));

		// add a list
		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart);

		// now add all this to the document
		document.add(catPart);

		// Next section
		anchor = new Anchor("Second Chapter", catFont);
		anchor.setName("Second Chapter");

		// Second parameter is the number of the chapter
		catPart = new Chapter(new Paragraph(anchor), 1);

		subPara = new Paragraph("Subcategory", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("This is a very important message"));

		// now add all this to the document
		document.add(catPart);

	}

	private static void createTable(Section subCatPart)
			throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);
	}

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	public static void createPDFForSubscriber(java.util.List<Subscriber> subscribers, 
											  String fileName) {

		try {
			
			Document document = new Document();			
			String filePath = "C:\\pdf\\" + fileName;
			FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
			PdfWriter.getInstance(document, fileOutputStream);
			document.open();

			Paragraph preface = new Paragraph();
			NotificationUtil.addEmptyLine(preface, 1);
			preface.add(new Paragraph("Notifications", catFont));
			document.addTitle("Notifications");
			NotificationUtil.addEmptyLine(preface, 1);

			document.addHeader(
					"Notifications",
					"D:/dev/logicielfixent/Publishing/src/com/fixent/publish/client/common/Header_Image.png");

			PdfPTable table = new PdfPTable(2);
			PdfPCell c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			if (subscribers != null && !subscribers.isEmpty()) {

				Map<Integer, Subscriber> map = new HashedMap();
				int i = 1;
				for (Subscriber subscriber : subscribers) {
					
					map.put(i, subscriber);
					i++;
				}
				
				for (int j = 1; j <= map.size(); ) {
					
					Subscriber info = map.get(j);
					Subscriber info2 = map.get(j+1);
					
					String fullAddress = "";
					if (info != null) {
						
						fullAddress = getReportAddress(info);
					}
					c1 = new PdfPCell(new Phrase(fullAddress));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);
					
					String fullAddress1 = "";
					if (info2 != null) {
						
						fullAddress1 = getReportAddress(info2);
					}
					c1 = new PdfPCell(new Phrase(fullAddress1));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);
					j = j+2;
				}
			}

			NotificationUtil.addEmptyLine(preface, 3);
			document.add(table);
			// Start a new page
			document.newPage();
			document.close();
			openPDF(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getReportAddress(Subscriber subscriber) {
		

		StringBuffer fullAddress = new StringBuffer();
		fullAddress.append("   \n");
		fullAddress.append(String.valueOf(subscriber.getCode()) + "\n");
		fullAddress.append(subscriber.getName()	+ "\n");
		fullAddress.append(subscriber.getAddress().getStreet1()	+ "\n");
		fullAddress.append(subscriber.getAddress().getStreet2()	+ "\n");
		fullAddress.append(subscriber.getAddress().getCity() + "\n");
		fullAddress.append(subscriber.getAddress().getState() + "\n");
		fullAddress.append(subscriber.getAddress().getPincode()	+ "\n");
		fullAddress.append("   \n");
		return fullAddress.toString();
	
	}
	public static void createPDFForSubscriberInfo(
			java.util.List<SubscribeInfo> subscribeInfos, boolean isSingle,
			String fileName) {

		try {
			Document document = new Document();
			String filePath = "C:\\pdf\\" + fileName;
			PdfWriter.getInstance(document, new FileOutputStream(new File(
					filePath)));
			document.open();

			Paragraph preface = new Paragraph();
			// We add one empty line
			NotificationUtil.addEmptyLine(preface, 1);
			// Lets write a big header
			preface.add(new Paragraph("Notifications", catFont));
			document.addTitle("Notifications");

			NotificationUtil.addEmptyLine(preface, 1);

			document.addHeader(
					"Notifications",
					"D:/dev/logicielfixent/Publishing/src/com/fixent/publish/client/common/Header_Image.png");

			PdfPTable table = new PdfPTable(2);
			PdfPCell c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			//
			// c1 = new PdfPCell(new Phrase("Mobile Number"));
			// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// table.addCell(c1);
			//
			// c1 = new PdfPCell(new Phrase("Book Name"));
			// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// table.addCell(c1);
			//
			// c1 = new PdfPCell(new Phrase("Address"));
			// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// table.addCell(c1);

			if (subscribeInfos != null && !subscribeInfos.isEmpty()) {

				Map<Integer, SubscribeInfo> map = new HashedMap();
				int i = 1;
				for (SubscribeInfo subscribeInfo : subscribeInfos) {
					
					map.put(i, subscribeInfo);
					i++;
				}
				
				for (int j = 1; j <= map.size(); ) {
					
					SubscribeInfo info = map.get(j);
					SubscribeInfo info2 = map.get(j+1);
					
					StringBuffer fullAddress = new StringBuffer();
					if (info != null) {
						
						fullAddress.append("   \n");
						fullAddress.append(String.valueOf(info
								.getSubscriber().getCode()) + "\n");
						fullAddress.append(info.getSubscriber().getName()
								+ "\n");
						fullAddress.append(info.getSubscriber()
								.getAddress().getStreet1()
								+ "\n");
						fullAddress.append(info.getSubscriber()
								.getAddress().getStreet2()
								+ "\n");
						fullAddress.append(info.getSubscriber()
								.getAddress().getCity()
								+ "\n");
						fullAddress.append(info.getSubscriber()
								.getAddress().getState()
								+ "\n");
						fullAddress.append(info.getSubscriber()
								.getAddress().getPincode()
								+ "\n");
						SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
								"dd-MMM-yyyy");
						String date1 = DATE_FORMAT.format(info
								.getExpiredDate());
						fullAddress.append(date1 + "\n");
						fullAddress.append("   \n");

						

						
					}
					c1 = new PdfPCell(new Phrase(fullAddress.toString()));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);
					
					StringBuffer fullAddress1 = new StringBuffer();
					if (info2 != null) {
						
						fullAddress1.append("   \n");
						fullAddress1.append(String.valueOf(info2
								.getSubscriber().getCode()) + "\n");
						fullAddress1.append(info2.getSubscriber().getName()
								+ "\n");
						fullAddress1.append(info2.getSubscriber()
								.getAddress().getStreet1()
								+ "\n");
						fullAddress1.append(info2.getSubscriber()
								.getAddress().getStreet2()
								+ "\n");
						fullAddress1.append(info2.getSubscriber()
								.getAddress().getCity()
								+ "\n");
						fullAddress1.append(info2.getSubscriber()
								.getAddress().getState()
								+ "\n");
						fullAddress1.append(info2.getSubscriber()
								.getAddress().getPincode()
								+ "\n");
						SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
								"dd-MMM-yyyy");
						String date1 = DATE_FORMAT.format(info2
								.getExpiredDate());
						fullAddress1.append(date1 + "\n");
						fullAddress1.append("   \n");

						
					}
					c1 = new PdfPCell(new Phrase(fullAddress1.toString()));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);
					
					j = j+2;
				}
				

				/*for (SubscribeInfo subscribeInfo : subscribeInfos) {

					StringBuffer fullAddress = new StringBuffer();
					fullAddress.append("   \n");
					fullAddress.append(String.valueOf(subscribeInfo
							.getSubscriber().getCode()) + "\n");
					fullAddress.append(subscribeInfo.getSubscriber().getName()
							+ "\n");
					fullAddress.append(subscribeInfo.getSubscriber()
							.getAddress().getStreet1()
							+ "\n");
					fullAddress.append(subscribeInfo.getSubscriber()
							.getAddress().getStreet2()
							+ "\n");
					fullAddress.append(subscribeInfo.getSubscriber()
							.getAddress().getCity()
							+ "\n");
					fullAddress.append(subscribeInfo.getSubscriber()
							.getAddress().getState()
							+ "\n");
					fullAddress.append(subscribeInfo.getSubscriber()
							.getAddress().getPincode()
							+ "\n");
					SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
							"dd-MMM-yyyy");
					String date1 = DATE_FORMAT.format(subscribeInfo
							.getExpiredDate());
					fullAddress.append(date1 + "\n");
					fullAddress.append("   \n");

					// table.addCell(subscribeInfo.getSubscriber() != null ?
					// String
					// .valueOf(subscribeInfo.getSubscriber().getId())
					// : null);
					// table.addCell(subscribeInfo.getSubscriber() != null ?
					// subscribeInfo
					// .getSubscriber().getName() : null);
					// table.addCell(subscribeInfo.getSubscriber() != null ?
					// subscribeInfo
					// .getSubscriber().getMobileNumber() : null);
					// table.addCell(subscribeInfo.getBook() != null ?
					// subscribeInfo
					// .getBook().getName() : null);
					// String address =
					// subscribeInfo.getSubscriber().getAddress().getStreet() +
					// "\n" +
					// subscribeInfo.getSubscriber().getAddress().getCity() +
					// "\n" +
					// subscribeInfo.getSubscriber().getAddress().getState() +
					// "\n" +
					// subscribeInfo.getSubscriber().getAddress().getPincode();
					c1 = new PdfPCell(new Phrase(fullAddress.toString()));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);

				}*/
			}

			NotificationUtil.addEmptyLine(preface, 3);
			document.add(table);
			// Start a new page
			document.newPage();
			document.close();
			openPDF(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void openPDF(String fileName) {

		try {

			if ((new File(fileName)).exists()) {

				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + fileName);
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
