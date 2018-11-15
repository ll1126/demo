package com.test.demo.core.redis.key;

public interface KeyPrefix {

    int expirSeconds();

    String getPrefix();

}
