package com.test.demo.modular.sys.service;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.util.JsonResult;

import javax.servlet.http.HttpServletResponse;


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
     * @param managerUser
     * @param roleId
     * @param isUpdate 0: 新增  1：修改
     * @return
     */
    String insertUser(ManagerUser managerUser, Integer roleId,Integer isUpdate);

    /**
     * 导出所有用户
     * @param response
     */
    void downUser(HttpServletResponse response);
}
