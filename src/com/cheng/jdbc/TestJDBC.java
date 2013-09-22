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
			//first load ORACLE DRIVER ,this will automatic register to DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//second get connection ,this will use proper driver
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "123456");
			
			statement = conn.createStatement();
			
			resultSet = statement.executeQuery("select t.insprdcode from topcheer_agentrate t");
			
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				//finally must close connection
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
