package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@Api(description = "菜单接口", value = "")
public class MenuController {

    @Resource
    MenuService menuService;

    /**
     * 读取用户的菜单
     *
     * @return
     */
    @GetMapping("/selMenu")
    public JsonResult selMenu(ManagerUser user) {
        return menuService.selMenu(user);
    }

    /**
     * 根据条件查找菜单
     *
     * @param parentId 父节点id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selAllMenu")
    public JsonResult selAllMenu(Integer parentId, Integer pageNum, Integer pageSize) {
        return menuService.selAllMenu(parentId, pageNum, pageSize);
    }

    /**
     * 新增一个菜单 / 修改一个菜单
     *
     * @param menu     菜单实体类
     * @param isUpdate true 修改 false 添加
     * @return
     */
    @PostMapping("/insertMenu")
    public JsonResult insertMenu(Menu menu, boolean isUpdate,ManagerUser user) {
        return menuService.insertMenu(menu, isUpdate,user);
    }

    /**
     * 删除一个菜单（包括其下子菜单）
     *
     * @param id 菜单id
     * @return
     */
    @GetMapping("/delMenu")
    public JsonResult delMenu(Integer id) {
        menuService.delMenu(id);
        return new JsonResult();
    }

    /**
     * 新增一个角色时查询所有菜单
     *
     * @return
     */
    @PostMapping("/selectMenuForRole")
    public JsonResult selectMenuForRole() {
        List<Map> menuList = menuService.selectMenuForRole();
        return new JsonResult(menuList);
    }
}
