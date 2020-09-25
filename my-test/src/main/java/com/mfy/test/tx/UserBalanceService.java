package com.mfy.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserBalanceService {

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private UserService userService;

	@Transactional
	public void insert(String name,String acount) {
		userService.insertUser(name);
		try{
			balanceService.insertBalance(name, acount);
		} catch (RuntimeException e){
			e.printStackTrace();
		}
	}


	public void test(){
		System.out.println("----------------------");
	}
}
