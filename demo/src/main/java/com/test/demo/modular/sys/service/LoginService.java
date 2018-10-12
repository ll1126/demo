package com.test.demo.modular.sys.service;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.util.JsonResult;


public interface LoginService {

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    JsonResult login(String userName, String password);
}
