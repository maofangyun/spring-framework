package com.mfy.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserBalanceService {

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private UserService userService;

	/**
	 * NESTED传播机制，如果子事务异常，父级可以捕获它的异常而不进行回滚，正常提交
	 * 如果父级异常，子事务必然回滚，这就是和 REQUIRES_NEW 的区别
	 * */
	@Transactional
	public void insert(String name,String acount) throws SQLException {
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationHandler());
		userService.insertUser(name);
		try {
			balanceService.insertBalance(name, acount);
		}
		catch (RuntimeException e){
			e.printStackTrace();
		}
	}


	public void test(){
		System.out.println("----------------------");
	}
}
