package com.mfy.test.tx;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	private static Connection conn;
	private static ComboPooledDataSource ds=null;
	static {
		try {
			ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.cj.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://39.98.237.119:3307/detection");
			ds.setUser("root");
			ds.setPassword("123456");
			ds.setMinPoolSize(10);
			ds.setMaxPoolSize(100);
			ds.setMaxIdleTime(1800);
			ds.setAcquireIncrement(3);
			ds.setMaxStatements(1000);
			ds.setInitialPoolSize(10);
			ds.setIdleConnectionTestPeriod(60);
			ds.setAcquireRetryAttempts(30);
			ds.setBreakAfterAcquireFailure(false);
			ds.setTestConnectionOnCheckout(false);
			ds.setAcquireRetryDelay(100);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
