package com.mfy.test.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Cacheable(cacheNames = "mapCache",key = "'jack' + #id")
	public String getCache(String id) {
		System.out.println("@Cacheable从方法中获取值");
		return "@Cacheable";
	}

    @CachePut(cacheNames = "mapCache",key = "'jack' + #id")
    public String putCache(String id) {
        System.out.println("======CacheServiceImpl.queryData");
        return "@CachePut";
    }

}
