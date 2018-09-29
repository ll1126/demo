package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.modular.sys.service.LoginService;
import com.test.demo.modular.sys.service.userService;
import com.test.demo.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/login")
@Api(description = "登陆接口", value = "")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登陆接口
     *
     * @param userName 登录名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public JsonResult insertUser(String userName,String password) {
        return loginService.login(userName,password);
    }

}
