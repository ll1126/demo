package com.test.demo.modular.sys.service;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.util.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;


public interface userService {
    /**
     * 查询所有用户（分页）
     *
     * @param pageNum
     * @param pageSize
     * @param managerUserCode
     * @return
     */
    JsonResult selUserList(Integer pageNum, Integer pageSize, ManagerUserCode managerUserCode);

    /**
     * 新增一个用户 / 修改已有用户
     *
     * @param managerUser 用户实体类
     * @param roleId      角色id
     * @param isUpdate    true： 修改 false： 新增
     * @return
     */
    String insertUser(ManagerUser managerUser, Integer roleId, boolean isUpdate);

    /**
     * 导出所有用户
     *
     * @param response
     */
    void downUser(HttpServletResponse response);

    /**
     * 删除用户
     *
     * @param id 要删除的用户id
     */
    void delUser(Integer id);

    /**
     * 修改密码
     *
     * @param user 当前登录的用户
     * @param oldPwd       旧密码
     * @param firstNewPwd  第一次新密码
     * @param secondNewPwd 第二次新密码
     * @return
     */
    String updatePwd(ManagerUser user, String oldPwd, String firstNewPwd, String secondNewPwd);

    String asyncTest() throws ExecutionException, InterruptedException;

}
