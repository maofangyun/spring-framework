package com.mfy.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService {

	@Autowired
	private DynamicDataSource dataSource;

	@Transactional(propagation= Propagation.NESTED, rollbackFor = {SQLException.class})
	public void insertUser(String user) throws SQLException {
		DynamicDataSource.setDataSource("ds1");
		Connection connection = dataSource.getConnection();
		String sql = "INSERT INTO user(user) VALUES (?)";
		//获取PreparedStatement对象
		PreparedStatement ps = connection.prepareStatement(sql);
		//对sql语句对占位符进行动态赋值
		ps.setString(1,user);
		//执行更新操作
		ps.executeUpdate();
		ps.close();
	}
}
