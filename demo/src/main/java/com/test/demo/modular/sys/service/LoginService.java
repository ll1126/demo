package com.test.demo.modular.sys.service;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.util.JsonResult;


public interface LoginService {

    /**
     * 登陆
     * @return
     * @param userName
     * @param password
     */
    JsonResult login(String userName, String password);
}
