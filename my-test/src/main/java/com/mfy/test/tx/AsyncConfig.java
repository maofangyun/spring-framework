package com.mfy.test.tx;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//核心线程数
		executor.setCorePoolSize(10);
		//最大线程数
		executor.setMaxPoolSize(20);
		//队列大小
		executor.setQueueCapacity(1000);
		//线程最大空闲时间
		executor.setKeepAliveSeconds(300);
		//指定用于新创建的线程名称的前缀
		executor.setThreadNamePrefix("mfy-Executor-");
		// 拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		// 这一步千万不能忘了,否则报错： java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
		executor.initialize();
		return executor;
	}

	// 异常处理器
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
