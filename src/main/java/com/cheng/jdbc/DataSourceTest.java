package com.cheng.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceTest {
	public static void main(String[] args) throws Exception {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("DATA serverName");
		dataSource.setDatabaseName("MySQL dbName");

		dataSource.setURL("jdbc:mysql://localhost:3306/test");

		// create jndi context
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:8088");
		InitialContext context = new InitialContext(env);
		context.bind("datasource", dataSource);

		MysqlDataSource jndiDataSource = (MysqlDataSource) context.lookup("datasource");
		Connection connection = jndiDataSource.getConnection("root", "root");
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from student t");

		if (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}

	}
}
