package com.mfy.test.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodClassKey;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheAdvice implements MethodInterceptor {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private LockManager lockManager;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Class<?> targetClass = AopUtils.getTargetClass(invocation);
		// 获取原型的方法(非桥接方法)
		Method specificMethod = AopUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
		MethodClassKey cacheKey = CachePointCut.getCacheKey(specificMethod, targetClass);
		CacheProperties cacheProperties = CachePointCut.ATTRIBUTE_CACHE.get(cacheKey);

		// 获取缓存数据库的key
		String key = getCacheKey(invocation,cacheProperties.getKey());
		// 从缓存数据库中获取值
		String cacheValue = getCacheValue(cacheProperties,key);
		if(cacheValue != null){
			// 序列化成方法的返回值类型
			return handlerType(invocation,cacheValue);
		}

		// 缓存数据库中找不到结果,进入加锁流程
		CacheLock cacheLock = null;
		try{
			LockProperties lockProperties = cacheProperties.getLockProperties();
			// 通过注解的lockName属性获取对应缓存锁
			cacheLock = lockManager.getCacheLock(lockProperties.getLockName());
			// 加锁
			cacheLock.lock(lockProperties.getExpire(), TimeUnit.MILLISECONDS,lockProperties.getKey());
			// 再查一遍缓冲,若其他线程已经将值写入缓存中,则不需要进行后续的操作
			cacheValue = getCacheValue(cacheProperties,key);
			if(cacheValue != null){
				// 序列化成方法的返回值类型
				return handlerType(invocation,cacheValue);
			}
			// 调用被代理的方法
			Object result = invocation.proceed();
			// 将返回值写入缓存中
			String[] cacheNames = cacheProperties.getCacheNames();
			for(String name : cacheNames){
				Cache cache = cacheManager.getCache(name);
				cache.put(cacheProperties.getKey(),result,cacheProperties.getExpire());
			}
			return result;
		} finally {
			if(cacheLock != null){
				cacheLock.unlock();
			}
		}
	}

	/**
	 * 处理返回的结果,序列化成返回值的类型
	 * */
	private Object handlerType(MethodInvocation invocation, String cacheValue) {
		Map<String, TypeHandler> types = applicationContext.getBeansOfType(TypeHandler.class);
		for (Map.Entry<String, TypeHandler> entry : types.entrySet()) {
			//根据调用的方法的返回值类型，找到该类型的处理类
			if(entry.getValue().support(invocation.getMethod().getGenericReturnType())) {
				return entry.getValue().handler(cacheValue,invocation.getMethod().getGenericReturnType());
			}
		}
		return null;
	}

	/**
	 * 获取缓存数据库中的值
	 * */
	private String getCacheValue(CacheProperties cacheProperties, String key) {
		String[] cacheNames = cacheProperties.getCacheNames();
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			Object value = cache.get(key);
			if (!StringUtils.isEmpty(value)) {
				return (String) value;
			}
		}
		return null;
	}

	/**
	 * 生成缓存数据库的key,以传入的key+方法入参名称+方法入参值混合
	 * */
	private String getCacheKey(MethodInvocation invocation,String key) {
		Object[] arguments = invocation.getArguments();
		String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(invocation.getMethod());
		return ElParser.getKey(key, parameterNames, arguments);
	}
}
