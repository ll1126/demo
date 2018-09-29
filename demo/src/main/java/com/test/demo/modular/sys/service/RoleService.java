package com.test.demo.modular.sys.service;


import com.test.demo.modular.sys.entity.Role;
import com.test.demo.util.JsonResult;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 添加一个角色
     */
    String addRole(Role role, String checkMenu);

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
     * @param id
     */
    void delRole(Integer id);

    /**
     * 加载所有可选角色
     *
     * @return
     */
    List<Map> loadRole();
}
