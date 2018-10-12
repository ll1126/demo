package com.test.demo.modular.sys.mapper;


import com.test.demo.modular.sys.entity.RoleMenu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Map;

/**
 * Title:RoleMenuService
 * Description:Map DAO层
 * Company:
 *
 * @author LinLei
 * @date 2018-07-02
 */
public interface RoleMenuMapper {

    /**
     * 生成角色和菜单关系
     */
    void insertRoleMenu(Map parames);

    /**
     * 删除角色对应关系
     *
     * @param roleId
     * @return
     */
    Integer delRoleMenu(Integer roleId);

}
