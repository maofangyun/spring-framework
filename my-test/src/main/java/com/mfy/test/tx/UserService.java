package com.mfy.test.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService {

	@Autowired
	private ComboPooledDataSource dataSource;

	@Transactional(propagation= Propagation.NESTED)
	public void insertUser(String user) throws SQLException {
		Connection connection = DataSourceUtils.getConnection(dataSource);
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
