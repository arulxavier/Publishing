package com.fixent.publish.client.notification.controller;

import java.io.File;
import java.io.FileOutputStream;

import com.fixent.publish.server.model.SubscribeInfo;
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
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class NotificationUtil {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
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

	public static void createPDFForSubscriber(
			java.util.List<SubscribeInfo> subscribeInfos, boolean isSingle, String fileName) {

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(new File(
					"D:/publishing/report/"+ fileName)));
			document.open();

			Paragraph preface = new Paragraph();
			// We add one empty line
			NotificationUtil.addEmptyLine(preface, 1);
			// Lets write a big header
			preface.add(new Paragraph("Notifications", catFont));
			document.addTitle("Notifications");

			NotificationUtil.addEmptyLine(preface, 1);
			
			document.addHeader("Notifications","D:/dev/logicielfixent/Publishing/src/com/fixent/publish/client/common/Header_Image.png");

			PdfPTable table = new PdfPTable(4);
			PdfPCell c1 = new PdfPCell(new Phrase("Subscriber ID"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Subscriber Name"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Mobile Number"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Book Name"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			if (subscribeInfos != null && !subscribeInfos.isEmpty()) {
				for (SubscribeInfo subscribeInfo : subscribeInfos) {
					table.addCell(subscribeInfo.getSubscriber() != null ? String
							.valueOf(subscribeInfo.getSubscriber().getId())
							: null);
					table.addCell(subscribeInfo.getSubscriber() != null ? subscribeInfo
							.getSubscriber().getName() : null);
					table.addCell(subscribeInfo.getSubscriber() != null ? subscribeInfo
							.getSubscriber().getMobileNumber() : null);
					table.addCell(subscribeInfo.getBook() != null ? subscribeInfo
							.getBook().getName() : null);
				}
			}

			NotificationUtil.addEmptyLine(preface, 3);
			document.add(table);
			// Start a new page
			document.newPage();
			document.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
