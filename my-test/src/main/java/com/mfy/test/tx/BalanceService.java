package com.mfy.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class BalanceService {

	@Autowired
	private DynamicDataSource dataSource;

	@Transactional(propagation= Propagation.REQUIRED,rollbackFor = {SQLException.class})
	public void insertBalance(String name,String acount) throws SQLException {
		DynamicDataSource.setDataSource("ds1");
		Connection connection = dataSource.getConnection();
		String sql = "INSERT INTO user_balance(name,balance) VALUES (?,?)";
		//获取PreparedStatement对象
		PreparedStatement ps = connection.prepareStatement(sql);
		//对sql语句对占位符进行动态赋值
		ps.setString(1,name);
		ps.setString(2,acount);
		//执行更新操作
		ps.executeUpdate();
		if(true) {
			throw new RuntimeException();
		}
		ps.close();
	}
}
