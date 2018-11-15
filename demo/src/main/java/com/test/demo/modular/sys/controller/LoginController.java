package com.test.demo.modular.sys.controller;

import com.test.demo.core.redis.RedisService;
import com.test.demo.modular.sys.service.LoginService;
import com.test.demo.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/login")
@Api(description = "登陆接口", value = "")
public class LoginController {

    @Resource
    private LoginService loginService;
    @Autowired
    private RedisService redisService;

    /**
     * 登陆接口
     *
     * @param userName 登录名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public JsonResult Login(String userName, String password) {
        return loginService.login(userName, password);
    }

    /**
     * 退出接口
     *
     * @return
     */
    @PostMapping("/signOut")
    public void signOut(HttpServletRequest httpServletRequest) {
        //
    }

    @GetMapping("/redis/set")
    public JsonResult set(String name ,String user) {
//        String b = redisService.get(UserKey.getById,"hello",String.class);
//        //设置完成的key格式 [类名字]:[UserKey.getById所得的值][自己传的key]
//        boolean bl = redisService.set(UserKey.getById,"hello","jack");

        return new JsonResult(0,null,null);
    }


}
