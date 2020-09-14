package com.mfy.test.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class UserBalanceService {

	@Transactional
	public void insert(String name,String acount){
		insertUser(name);
		insertBalance(name, acount);
	}

	@Transactional(propagation= Propagation.REQUIRED)
	public void insertBalance(String name,String acount){
		Connection connection = C3P0Util.getConnection();
		String sql = "INSERT INTO user_balance(name,balance) VALUES (?,?)";
		try{
			//获取PreparedStatement对象
			PreparedStatement ps = connection.prepareStatement(sql);
			//对sql语句对占位符进行动态赋值
			ps.setString(1,name);
			ps.setString(2,acount);
			//执行更新操作
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			C3P0Util.close(connection);
		}
	}


	@Transactional(propagation= Propagation.REQUIRED)
	public void insertUser(String user){
		Connection connection = C3P0Util.getConnection();
		String sql = "INSERT INTO user(user) VALUES (?)";
		try{
			//获取PreparedStatement对象
			PreparedStatement ps = connection.prepareStatement(sql);
			//对sql语句对占位符进行动态赋值
			ps.setString(1,user);
			//执行更新操作
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			C3P0Util.close(connection);
		}
	}
}
