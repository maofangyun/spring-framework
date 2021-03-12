package com.mfy.test.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {


    @Bean
    public DataSource dynamicDataSource() {

		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://47.92.1.153:3307/detection?useSSL=false&serverTimezone=UTC");
			dataSource.setUser("root");
			dataSource.setPassword("123456");
			dataSource.setMinPoolSize(10);
			dataSource.setMaxPoolSize(100);
			dataSource.setMaxIdleTime(1800);
			dataSource.setAcquireIncrement(3);
			dataSource.setMaxStatements(1000);
			dataSource.setInitialPoolSize(10);
			dataSource.setIdleConnectionTestPeriod(60);
			dataSource.setAcquireRetryAttempts(30);
			dataSource.setBreakAfterAcquireFailure(false);
			dataSource.setTestConnectionOnCheckout(false);
			dataSource.setAcquireRetryDelay(100);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return dataSource;
    }
}
