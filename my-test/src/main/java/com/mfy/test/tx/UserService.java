package com.mfy.test.tx;

import com.mfy.test.ioc.User;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService {

	@Autowired
	private DataSource dataSource;

	@Transactional(propagation= Propagation.NESTED, rollbackFor = {SQLException.class})
	public void insertUser(User user) throws SQLException {
		ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
		Connection connection = connectionHolder.getConnection();
		System.out.println(connection.getAutoCommit());
		String sql = "INSERT INTO user(name,sex,login_time,role_id) VALUES (?,?,?,?)";
		//获取PreparedStatement对象
		PreparedStatement ps = connection.prepareStatement(sql);
		//对sql语句对占位符进行动态赋值
		ps.setString(1,user.getName());
		ps.setString(2,user.getSex());
		ps.setDate(3,new Date(System.currentTimeMillis()));
		ps.setInt(4,1);
		//执行更新操作
		ps.executeUpdate();
		ps.close();
	}
}
