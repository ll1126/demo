package com.test.demo.modular.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.mapper.MenuMapper;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 读取用户的菜单
     *
     * @param roleId 角色id
     * @return
     */
    public JsonResult selMenu(String roleId) {
        System.out.println("roleId:" + roleId);
        List<Map> list = menuMapper.selMenu(Integer.valueOf(roleId));
        return new JsonResult(list);
    }

    /**
     * 创建一个菜单
     *
     * @param menu 菜单实体类
     * @param isUpdate true: 修改菜单 false: 添加菜单
     * @param userName
     * @return
     */
    public JsonResult insertMenu(Menu menu, boolean isUpdate, String userName) {
        if (isUpdate) {
            //修改
            menuMapper.updateMenu(menu);
            return new JsonResult(0, null, "修改菜单成功");
        } else {
            menu.setTdate(new Date());
            menu.setCcreateuser(userName);
            menuMapper.getInsert(menu);
            return new JsonResult(0, null, "添加菜单成功");
        }

    }

    /**
     * 根据条件查找菜单
     *
     * @param parentId 父节点
     * @param pageNum
     * @param pageSize
     * @return
     */
    public JsonResult selAllMenu(Integer parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> list = menuMapper.selAllMenu(parentId);
        // 取分页信息
        PageInfo pageInfo = new PageInfo(list);
        Page page = PageUtils.getConvertPage(pageNum, pageSize, list, pageInfo);
        //查一下上一级的父id

        return new JsonResult(page);
    }

    /**
     * 删除一个菜单 （包括其下子菜单）
     *
     * @param id 菜单id
     * @return
     */
    public void delMenu(Integer id) {
        menuMapper.delMenu(id);
    }

    /**
     * 新增一个角色时查询所有菜单
     */
    public List<Map> selectMenuForRole() {
        return menuMapper.selMenuForRole();
    }
}
