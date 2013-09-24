package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestBatch {
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
			/*
			
			statement = conn.createStatement();

			statement.addBatch("insert into student values (7,'xiao1',0)");
			statement.addBatch("insert into student values (8,'xiao2',1)");
			statement.addBatch("insert into student values (9,'xiao3',2)");
			
			statement.executeBatch();
			 */
			
			statement = conn.prepareStatement("insert into student values (?,?,?)");
			
			statement.setInt(1, 9);
			statement.setString(2, "xiaoming");
			statement.setInt(3, 5);
			statement.addBatch();
			
			statement.setInt(1, 19);
			statement.setString(2, "xiaozhang");
			statement.setInt(3, 5);
			statement.addBatch();
			
			statement.executeBatch();
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
