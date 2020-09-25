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
    public ComboPooledDataSource dynamicDataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://39.98.237.119:3307/detection");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
            comboPooledDataSource.setMinPoolSize(10);
            comboPooledDataSource.setMaxPoolSize(100);
            comboPooledDataSource.setMaxIdleTime(1800);
            comboPooledDataSource.setAcquireIncrement(3);
            comboPooledDataSource.setMaxStatements(1000);
            comboPooledDataSource.setInitialPoolSize(10);
            comboPooledDataSource.setIdleConnectionTestPeriod(60);
            comboPooledDataSource.setAcquireRetryAttempts(30);
            comboPooledDataSource.setBreakAfterAcquireFailure(false);
            comboPooledDataSource.setTestConnectionOnCheckout(false);
            comboPooledDataSource.setAcquireRetryDelay(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("ds1",comboPooledDataSource);
//
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        dynamicDataSource.setDefaultTargetDataSource(comboPooledDataSource);
        return comboPooledDataSource;
    }
}
