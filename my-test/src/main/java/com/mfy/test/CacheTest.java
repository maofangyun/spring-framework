package com.mfy.test;

import com.mfy.test.cache.CacheConfig;
import com.mfy.test.cache.CacheService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CacheTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(CacheConfig.class);
		ac.refresh();
		CacheService ubs = ac.getBean(CacheService.class);
		ubs.getCache("1");
		ubs.putCache("2");
	}
}
