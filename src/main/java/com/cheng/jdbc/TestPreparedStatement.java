package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPreparedStatement {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// first load ORACLE DRIVER ,this will automatic register to
			// DriverManager
			Class.forName("com.mysql.jdbc.Driver");

			// second get connection ,this will use proper driver
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "123456");
			//switch driver to mysql
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			statement = conn.prepareStatement("insert into student values(?,?,?)");

			statement.setInt(1, 5);
			statement.setString(2, "zhang");
			statement.setString(3, "15");
			
			System.out.println(statement.executeUpdate());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// finally must close connection
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
