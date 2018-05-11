package com.test.demo.controller;

import com.test.demo.entity.Menu;
import com.test.demo.entity.User;
import com.test.demo.service.MenuService;
import com.test.demo.service.userService;
import com.test.demo.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    userService userService;
    @Resource
    MenuService menuService;

    @PostMapping("/selUser")
    public JsonResult selUser(){
        List<User> list = userService.selUser();
        System.out.println(list);
        return new JsonResult();
    }

    @GetMapping("/selMenu")
    public JsonResult selMenu(){
        List<Menu> list = menuService.selMenu();
        System.out.println(list);
        return new JsonResult(list);
    }
}
