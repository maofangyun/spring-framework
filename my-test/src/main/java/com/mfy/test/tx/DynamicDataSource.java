package com.mfy.test.tx;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        String ds = "ds1";

        System.out.println("=========选择的数据源：" + ds);
        return ds;
    }
}
