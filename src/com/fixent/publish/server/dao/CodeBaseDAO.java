package com.fixent.publish.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.fixent.publish.server.common.BaseDAO;

public class CodeBaseDAO 
extends BaseDAO {

	@SuppressWarnings("deprecation")
	public int getCodeValue(String code) throws Exception {

		int value = 0;
		
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/bookpup";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "root", "admin@123");

		// create the java mysql update preparedstatement
		String query = "SELECT VALUE FROM bookpup.catagory WHERE CODE = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, code);
		resultSet = preparedStmt.executeQuery();
		/*Session session = getSession();
		connection = session.connection();

		preparedStatement = connection.prepareStatement("SELECT VALUE FROM bookpup.catagory WHERE CODE = ?");
		preparedStatement.setString(1, code);
		
		resultSet = preparedStatement.executeQuery();*/
		
		while (resultSet.next()) {
			value = resultSet.getInt(1);			
		}
//		session.close();
		conn.close();
		return value;
	}

	@SuppressWarnings("deprecation")
	public boolean createCode(String code, int value) 
	throws Exception {
		
		
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/bookpup";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "root", "admin@123");

		// create the java mysql update preparedstatement
		String query = "insert into bookpup.catagory values (default, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, code);
		preparedStmt.setInt(2, value);

		// execute the java preparedstatement
		int status = preparedStmt.executeUpdate();

		conn.close();
	/*	Session session = getSession();
		connection = session.connection();
		preparedStatement = connection
		          .prepareStatement("insert into bookpup.catagory values (default, ?, ?)");
		preparedStatement.setString(1, code);
	    preparedStatement.setInt(2, value);
	    int status = preparedStatement.executeUpdate();
	    session.close();*/
	    if (status == 0) {
	    	return false;
	    } else {
	    	return true;
	    }
	}

	@SuppressWarnings("deprecation")
	public boolean updateCode(String code, int value) 
	throws Exception {
		
		
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/bookpup";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "root", "admin@123");

		// create the java mysql update preparedstatement
		String query = "UPDATE bookpup.catagory SET VALUE = ? WHERE CODE = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, value);
		preparedStmt.setString(2, code);

		// execute the java preparedstatement
		int status = preparedStmt.executeUpdate();

		conn.close();
		/*Session session = getSession();
		connection = session.connection();
		preparedStatement = connection
		          .prepareStatement("UPDATE bookpup.catagory SET VALUE = ? WHERE CODE = ?;");
		preparedStatement.setInt(1, value);
	    preparedStatement.setString(2, code);
	    int status = preparedStatement.executeUpdate();
	    session.close();*/
	    if (status == 0) {
	    	return true;
	    } else {
	    	return false;
	    }
	}

}
