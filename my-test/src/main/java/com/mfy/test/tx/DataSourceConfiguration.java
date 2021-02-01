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
    public DynamicDataSource dynamicDataSource() {
        ComboPooledDataSource dataSource1 = new ComboPooledDataSource();
        try {
            dataSource1.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource1.setJdbcUrl("jdbc:mysql://39.98.237.119:3307/detection");
            dataSource1.setUser("root");
            dataSource1.setPassword("123456");
            dataSource1.setMinPoolSize(10);
            dataSource1.setMaxPoolSize(100);
            dataSource1.setMaxIdleTime(1800);
            dataSource1.setAcquireIncrement(3);
            dataSource1.setMaxStatements(1000);
            dataSource1.setInitialPoolSize(10);
            dataSource1.setIdleConnectionTestPeriod(60);
            dataSource1.setAcquireRetryAttempts(30);
            dataSource1.setBreakAfterAcquireFailure(false);
            dataSource1.setTestConnectionOnCheckout(false);
            dataSource1.setAcquireRetryDelay(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

		ComboPooledDataSource dataSource2 = new ComboPooledDataSource();
		try {
			dataSource2.setDriverClass("com.mysql.cj.jdbc.Driver");
			dataSource2.setJdbcUrl("jdbc:mysql://47.92.1.153:3307/localtest?useSSL=false&serverTimezone=UTC");
			dataSource2.setUser("root");
			dataSource2.setPassword("123456");
			dataSource2.setMinPoolSize(10);
			dataSource2.setMaxPoolSize(100);
			dataSource2.setMaxIdleTime(1800);
			dataSource2.setAcquireIncrement(3);
			dataSource2.setMaxStatements(1000);
			dataSource2.setInitialPoolSize(10);
			dataSource2.setIdleConnectionTestPeriod(60);
			dataSource2.setAcquireRetryAttempts(30);
			dataSource2.setBreakAfterAcquireFailure(false);
			dataSource2.setTestConnectionOnCheckout(false);
			dataSource2.setAcquireRetryDelay(100);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put("ds1",dataSource1);
        targetDataSources.put("ds2",dataSource2);

        return new DynamicDataSource(dataSource1,targetDataSources);
    }
}
