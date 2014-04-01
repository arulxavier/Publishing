package com.fixent.publish.server.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JavaMysqlPreparedStatementUpdateExample {

	public static void main(String[] args) {
		try {
			// create a java mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/bookpup";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "admin@123");

			// create the java mysql update preparedstatement
			String query = "update users set num_points = ? where first_name = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, 12);
			preparedStmt.setString(2, "Fred");

			// execute the java preparedstatement
			int value = preparedStmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
