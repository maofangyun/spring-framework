package com.mfy.test.advisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheManager {

	private static final HashMap<String,Cache> CACHE_MAP = new HashMap<>();

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	private void init(){
		Map<String, Cache> beans = applicationContext.getBeansOfType(Cache.class);
		beans.entrySet().stream().forEach(v -> {
			CACHE_MAP.putIfAbsent(v.getKey(),v.getValue());
		});
	}

	public Cache getCache(String key){
		return CACHE_MAP.get(key);
	}

}
