package com.test.demo.modular.sys.mapper;


import com.test.demo.modular.sys.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Title:RoleService
 * Description:Map DAO层
 * Company:
 * @author LinLei
 * @date 2018-07-02
 */
public interface RoleMapper {
    /**
     * 新增一个角色
     * @param role
     */
    void insertRole(Role role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectRole();

    /**
     * 删除一个角色
     * @param RoleId
     * @return
     */
    Integer delRole(Integer RoleId);

    /**
     * 加载所有可选角色
     * @return
     */
    List<Map> loadRole();
   
}
