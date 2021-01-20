package com.mfy.test.advisor;

public interface Cache {

    String getName();

    <T> T get(Object key);

    void put(Object key, Object value);

    void put(Object key, Object value,int expire);

    void evict(Object key);
}
