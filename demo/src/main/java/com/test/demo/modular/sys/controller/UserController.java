package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.modular.sys.service.userService;
import com.test.demo.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
@Api(description = "用户接口", value = "")  //类注解
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
    @ApiOperation(value = "查询所有用户（分页）") //方法名注解
    @GetMapping("/selUserList")
    public JsonResult selUserList(Integer pageNum, Integer pageSize, ManagerUserCode managerUserCode) {
        return userService.selUserList(pageNum, pageSize, managerUserCode);
    }

    /**
     * 新增一个用户 / 修改已有用户
     * 根据 isUpdate 判断 true： 修改 false： 新增
     *
     * @param managerUser 用户实体类
     * @return
     */
    @PostMapping("/insertUser")
    public JsonResult insertUser(ManagerUser managerUser, Integer roleId, boolean isUpdate) {
        String res = userService.insertUser(managerUser, roleId, isUpdate);
        if (res == null) {
            if (!isUpdate) {
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
     * @param id 用户id
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
     * @return
     */
    @PostMapping("/updatePwd")
    public JsonResult updatePwd(String oldPwd, String firstNewPwd, String secondNewPwd, ManagerUser user) {
        String res = userService.updatePwd(user, oldPwd, firstNewPwd, secondNewPwd);
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
    public void downUser(HttpServletResponse response) {
        userService.downUser(response);
    }

    /**
     * 测试
     *
     * @throws IOException
     */
    @GetMapping("/api/async")
    public void async() throws IOException, ExecutionException, InterruptedException {
        userService.asyncTest();
    }

    @RequestMapping("/api/index")
    public String userList2(Model model) throws Exception {
        model.addAttribute("hello","Hello, Spring Boot!");
        return "/Hello";
    }


}
