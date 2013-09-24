package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSetScroll {
	public static void main(String[] args) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// first load ORACLE DRIVER ,this will automatic register to
			// DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// second get connection ,this will use proper driver
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "123456");

			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			statement.setFetchSize(1);

			resultSet = statement.executeQuery("select t.insprdcode from topcheer_agentrate t");

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
			
			//try get data scroll
			resultSet.absolute(2);
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getRow());
			
			Thread.sleep(40000);
			
			resultSet.last();
			System.out.println(resultSet.isLast());
			System.out.println(resultSet.isAfterLast());
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getRow());
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
