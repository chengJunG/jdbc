package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSetUpdate {
	public static void main(String[] args) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// first load ORACLE DRIVER ,this will automatic register to
			// DriverManager
			Class.forName("com.mysql.jdbc.Driver");

			// second get connection ,this will use proper driver
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "123456");
			//switch driver to mysql
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			statement.setFetchSize(1);

			resultSet = statement.executeQuery("select t.studentid, t.studentname, t.age from student t");

			// Executes the given SQL statement, which may be an INSERT, UPDATE,
			// or DELETE statement or an SQL statement that returns nothing,
			// such as an SQL DDL statement.
			// statement.executeUpdate(sql);

			//this just get data by order
			/*
			while (resultSet.next()) {
			 
				System.out.println(resultSet.getString(1));
			}
			*/
			
			//update data throw resultSet
			resultSet.moveToInsertRow();
			resultSet.updateInt(1, 11);
			resultSet.updateString(2, "update");
			resultSet.updateInt(3, 18);
			resultSet.insertRow();
			
			
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
