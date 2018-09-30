package com.test.demo.modular.sys.service;

import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.util.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface MenuService {
    /**
     * 读取用户的菜单
     *
     * @return
     * @param request
     */
    JsonResult selMenu(HttpServletRequest request);

    /**
     * 创建一个菜单
     *
     * @param menu
     * @return
     */
    void insertMenu(Menu menu);

    /**
     * 根据条件查找菜单
     *
     * @param parentId 父节点
     * @param pageNum
     * @param pageSize
     * @return
     */
    JsonResult selAllMenu(Integer parentId, Integer pageNum, Integer pageSize);

    /**
     * 删除一个菜单 （包括其下子菜单）
     * @param id 菜单id
     * @return
     */
    void delMenu(Integer id);

    /**
     * 新增一个角色时查询所有菜单
     */
    List<Map> selectMenuForRole();
}
