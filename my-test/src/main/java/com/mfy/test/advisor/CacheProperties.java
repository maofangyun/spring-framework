package com.mfy.test.advisor;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class CacheProperties {

	private String[] cacheNames;

	private int expire;

	private String key;

	private Method method;

	private LockProperties lockProperties;
}
