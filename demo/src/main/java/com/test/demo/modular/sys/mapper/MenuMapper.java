package com.test.demo.modular.sys.mapper;

import com.test.demo.modular.sys.entity.Menu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    /**
     * 根据用户角色查找可用菜单（目前找全部）
     * @param roleId 角色id
     * @return
     */
    List<Map> selMenu(Integer roleId);

    /**
     * 新增一个菜单
     *
     * @param menu
     */
    void getInsert(Menu menu);

    /**
     * 根据父节点查找所有菜单
     *
     * @param parentId 父节点
     * @return
     */
    List<Menu> selAllMenu(@Param("parentId") Integer parentId);

    /**
     * 删除一个菜单及其子菜单
     * @param id 菜单id
     * @return
     */
    int delMenu(@Param("id") Integer id);

    /**
     * 查询所有菜单节点的id和名字和子类（添加角色时勾选用）
     * @return
     */
    List<Map> selMenuForRole();
}
