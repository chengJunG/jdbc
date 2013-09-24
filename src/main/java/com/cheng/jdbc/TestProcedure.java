package com.cheng.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class TestProcedure {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement statement = null;
		try {
			// first load ORACLE DRIVER ,this will automatic register to
			// DriverManager
			Class.forName("com.mysql.jdbc.Driver");

			// second get connection ,this will use proper driver
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "root", "123456");
			//switch driver to mysql
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			
			statement = conn.prepareCall("call test(?,?)");
			
			statement.registerOutParameter(2, Types.VARCHAR);
			
			statement.setInt(1, 6);
			
			statement.execute();
			
			System.out.println(statement.getString(2));
			
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
