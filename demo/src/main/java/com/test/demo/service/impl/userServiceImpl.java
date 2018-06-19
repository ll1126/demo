package com.test.demo.service.impl;

import com.test.demo.entity.User;
import com.test.demo.mapper.userMapper;
import com.test.demo.service.userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Resource
    userMapper usermapper;

    @Override
    public List<User> selUser() {

        return usermapper.selUser();
    }
}
