package com.mfy.test.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.mfy.test.listener")
@EnableAsync
public class BeanConfig {

	/**
	 * 也可以不指定,Spring默认使用SimpleAsyncTaskExecutor来异步执行任务
	 * */
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
}
