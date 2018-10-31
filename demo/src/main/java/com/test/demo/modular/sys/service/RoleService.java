package com.test.demo.modular.sys.service;


import com.test.demo.modular.sys.entity.Role;
import com.test.demo.util.JsonResult;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 新增一个角色 /修改角色信息
     *
     * @param role      角色实体类
     * @param checkMenu 选中的菜单id 字符串
     * @param isUpdate  0：新增  1：修改
     * @return
     */
    String addRole(Role role, String checkMenu, Integer isUpdate);

    /**
     * 查询所有角色
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    JsonResult selRole(Integer pageNum, Integer pageSize);

    /**
     * 删除角色
     *
     * @param id 要删除的角色id
     */
    void delRole(Integer id);

    /**
     * 加载所有可选角色
     *
     * @return
     */
    List<Map> loadRole();



}
