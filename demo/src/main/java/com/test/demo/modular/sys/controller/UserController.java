package com.test.demo.modular.sys.controller;

import com.test.demo.core.auth.ATTUser;
import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.entity.User;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.modular.sys.service.userService;
import com.test.demo.util.JsonResult;
import com.test.demo.util.easyExcel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
     * @param managerUser 用户实体类
     * @return
     */
    @PostMapping("/insertUser")
    public JsonResult insertUser(ManagerUser managerUser, Integer roleId, Integer isUpdate) {
        String res = userService.insertUser(managerUser, roleId, isUpdate);
        if (res == null) {
            if (isUpdate != null && isUpdate == 0) {
                res = "添加用户成功";
            } else {
                res = "修改用户成功";
            }
        }
        return new JsonResult(0, null, res);
    }

    /**
     * 删除一个用户
     *
     * @param id 要删除的用户id
     * @return
     */
    @GetMapping("/delUser")
    public JsonResult delRole(Integer id) {
        userService.delUser(id);
        return new JsonResult(0, null, "删除成功");
    }

    /**
     * 修改密码
     *
     * @param oldPwd             旧密码
     * @param firstNewPwd        第一次新密码
     * @param secondNewPwd       第二次新密码
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/updatePwd")
    public JsonResult updatePwd(String oldPwd, String firstNewPwd, String secondNewPwd, HttpServletRequest httpServletRequest) {
        String userId = String.valueOf(httpServletRequest.getAttribute(ATTUser.USER_TOKEN));
        String res = userService.updatePwd(userId, oldPwd, firstNewPwd, secondNewPwd);
        if (res != null) {
            return new JsonResult(1, null, res);
        }
        return new JsonResult(0, null, "修改密码成功");
    }

    /**
     * 导出用户 （Excel文件）
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/api/downUser")
    public void downUser(HttpServletResponse response) throws IOException {
        userService.downUser(response);
    }


}
