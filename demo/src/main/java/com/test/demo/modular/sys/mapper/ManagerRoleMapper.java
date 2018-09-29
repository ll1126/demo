package com.test.demo.modular.sys.mapper;


import com.test.demo.modular.sys.entity.ManagerRole;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Map;

/**
 * Title:ManagerRoleService
 * Description:Map DAO层
 * Company:
 *
 * @author LinLei
 * @date 2018-07-05
 */
public interface ManagerRoleMapper {
    /**
     * 新增一条用户和角色关系
     *
     * @param managerRole
     */
    void getInsert(ManagerRole managerRole);

    /**
     * 根据用户id查询对应的角色id
     * @return
     */
    Integer selRoleByUserId(Integer userId);

    /**
     * 修改用户的角色对应id
     * @param userId
     * @param roleId
     * @return
     */
    Integer updateByManagerId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
