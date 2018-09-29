package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.entity.User;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.modular.sys.service.userService;
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
@Api(description = "用户接口", value = "")
public class UserController {

    @Resource
    private userService userService;

    /**
     * 查询所有用户（分页）
     *
     * @param pageNum
     * @param pageSize
     * @param managerUserCode 查询条件
     * @return
     */
    @GetMapping("/selUserList")
    public JsonResult selUserList(Integer pageNum, Integer pageSize, ManagerUserCode managerUserCode) {
        return userService.selUserList(pageNum, pageSize, managerUserCode);
    }

    /**
     * 新增一个用户 / 修改已有用户
     * 根据 isUpdate 判断 0： 新增  1： 修改
     *
     * @param managerUser
     * @return
     */
    @PostMapping("/insertUser")
    public JsonResult insertUser(ManagerUser managerUser,Integer roleId,Integer isUpdate) {
        String res = userService.insertUser(managerUser,roleId,isUpdate);
        if (res == null) {
            if(isUpdate!=null && isUpdate==0){
                res = "添加用户成功";
            }else{
                res = "修改用户成功";
            }
        }
        return new JsonResult(0, null, res);
    }
}
