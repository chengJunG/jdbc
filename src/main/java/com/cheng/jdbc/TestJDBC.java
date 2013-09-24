package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
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
			statement = conn.createStatement();

			resultSet = statement.executeQuery("select * from student t");

			// Executes the given SQL statement, which may be an INSERT, UPDATE,
			// or DELETE statement or an SQL statement that returns nothing,
			// such as an SQL DDL statement.
			// statement.executeUpdate(sql);

			//this just get data by order
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			
			//try get data scroll this will throw exception because have not set ResultSet type to scroll
			/*
			resultSet.absolute(2);
			System.out.println(resultSet.getInt(1));
			*/
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
