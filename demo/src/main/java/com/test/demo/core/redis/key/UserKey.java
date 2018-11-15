package com.test.demo.core.redis.key;

public class UserKey extends BasePrefix{

    public UserKey(int seconds,String prefix) {
        super(seconds,prefix);
    }

    public static UserKey getById = new UserKey(10,"id");

}
