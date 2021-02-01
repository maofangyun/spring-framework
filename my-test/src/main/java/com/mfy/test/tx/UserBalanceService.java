package com.mfy.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

	@Autowired
	private UserBalanceService userBalanceService;

	/**
	 * NESTED传播机制,如果子事务异常,父级可以捕获它的异常而不进行回滚,正常提交;
	 * 但是如果父级异常,子事务必然回滚,这就是和REQUIRES_NEW的区别
	 * */
	@Transactional
	//@Async
	public void insert(String name,String acount) throws SQLException {
		// 事务同步处理器只能在这里注册,因为synchronizations只有在事务开启后,才会初始化
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationHandler());
		userService.insertUser(name);
		try {
			balanceService.insertBalance(name, acount);
		}
		catch (RuntimeException e){
			e.printStackTrace();
		}
	}

}
