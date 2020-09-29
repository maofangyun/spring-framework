package com.mfy.test.cache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableCaching
public class CacheBean {

    @Bean
    public FactoryBean<ConcurrentMapCache> mapCache() {
        ConcurrentMapCacheFactoryBean bean = new ConcurrentMapCacheFactoryBean();
        bean.setName("mapCache");
        return bean;
    }

//    @Bean
//    public CacheManager simpleCacheManager(@Qualifier("redisCache") Cache redisCache, @Qualifier("mapCache") Cache concurrentMapCacheFactoryBean) {
//        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//        List<Cache> list = new ArrayList<>();
//        list.add(redisCache);
//        list.add(concurrentMapCacheFactoryBean);
//        simpleCacheManager.setCaches(list);
//        return simpleCacheManager;
//    }
}
