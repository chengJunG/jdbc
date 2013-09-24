package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestTransaction {
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
			
			// By default, new connections are in auto-commit mode.
			conn.setAutoCommit(false);
			statement = conn.prepareStatement("insert into student values(?,?,?)");

			statement.setInt(1, 5);
			statement.setString(2, "zhang");
			statement.setString(3, "15");

			statement.executeUpdate();

			// commit
			conn.commit();
			conn.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (null != conn) {
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				// finally must close connection
				// before close should judge this is null
				if(null != statement && null != conn){
					statement.close();
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
