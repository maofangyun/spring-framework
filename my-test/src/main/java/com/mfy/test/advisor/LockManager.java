package com.mfy.test.advisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LockManager {

	private static final HashMap<String,CacheLock> LOCK_MAP = new HashMap<>();

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	private void init(){
		Map<String, CacheLock> beans = applicationContext.getBeansOfType(CacheLock.class);
		beans.entrySet().stream().forEach(v -> {
			LOCK_MAP.putIfAbsent(v.getKey(),v.getValue());
		});
	}

	public CacheLock getCacheLock(String key){
		return LOCK_MAP.get(key);
	}

}
