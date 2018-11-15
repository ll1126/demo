package com.test.demo.modular.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.mapper.MenuMapper;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 读取用户的菜单
     *
     * @param user 当前登录的用户
     * @return
     */
    public JsonResult selMenu(ManagerUser user) {
        List<Map> list = menuMapper.selMenu(user.getRoleId());
        return new JsonResult(list);
    }

    /**
     * 添加菜单 / 修改菜单
     *
     * @param menu 菜单实体类
     * @param isUpdate true: 修改菜单 false: 添加菜单
     * @param user 当前登录的用户
     * @return
     */
    public JsonResult insertMenu(Menu menu, boolean isUpdate, ManagerUser user) {
        if (isUpdate) {
            //修改
            menuMapper.updateMenu(menu);
            return new JsonResult(0, null, "修改菜单成功");
        } else {
            menu.setTdate(new Date());
            menu.setCcreateuser(String.valueOf(user.getId()));
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
        List<Menu> menus = menuMapper.selMenuForRole();
        List<Map> list = menus.stream().filter(menu -> menu.getFparentid() == 0).map(temp -> {
            Map map = new HashMap();
            set_map(map,temp);  //一级菜单 存入
            // 一级菜单的子类 （二级菜单）  --开始
            List<Map> two_list = menus.stream().filter(two_menu -> two_menu.getFparentid() == temp.getId()).map(two_temp -> {
                Map two_map = new HashMap();
                set_map(two_map,two_temp);  //二级菜单 存入
                // 二级菜单的子类 （按钮）  --开始
                List<Map> button_list = menus.stream().filter(button_menu -> button_menu.getFparentid() == two_temp.getId()).map(button_temp -> {
                    Map button_map = new HashMap();
                    set_map(button_map,button_temp);  //按钮 存入
                    return button_map;
                }).collect(Collectors.toList());
                // 二级菜单的子类 （按钮）  --结束
                two_map.put("children",button_list);
                return two_map;
            }).collect(Collectors.toList());
            // 一级菜单的子类 （二级菜单）  --结束
            map.put("children",two_list);  //
            return map;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 将菜单id 和菜单名字 放入map
     * @param map
     * @param menu
     */
    public void set_map(Map map,Menu menu) {
        map.put("id",menu.getId());  //菜单id
        map.put("label",menu.getMenuName());  //菜单名字
    }
}
