package com.test.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.entity.Menu;
import com.test.demo.entity.User;
import com.test.demo.service.MenuService;
import com.test.demo.service.userService;
import com.test.demo.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(description = "userapi", value = "")
public class userController {
    @Resource
    userService userService;
    @Resource
    MenuService menuService;

    @ApiOperation(value = "测试",notes="")
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

//    @GetMapping("/selMenu")
//    public JsonResult pageHelp(){
//        PageHelper.startPage(pageNum, pageSize);
//        List<ManagerUser> list = attendancerecordService.findManagements(managerUserCode);
//        // 取分页信息
//        PageInfo pageInfo = new PageInfo(list);
//        Page page = PageUtils.getConvertPage(pageNum, pageSize, list, pageInfo);
//    }


}
