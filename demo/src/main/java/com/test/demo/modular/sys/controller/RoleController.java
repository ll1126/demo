package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.entity.Role;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.modular.sys.service.RoleService;
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
@RequestMapping("/role")
@Api(description = "角色接口", value = "")
public class RoleController {

    @Resource
    RoleService roleService;

    /**
     * 新增一个角色
     *
     * @param role 角色实体类
     * @return
     */
    @PostMapping("/insertRole")
    public JsonResult insertRole(Role role,String checkMenu) {
        String res = roleService.addRole(role,checkMenu);
        if(res!=null){
            return new JsonResult(1,null,res);
        }
        return new JsonResult(0,null,"添加角色成功了耶( •̀ ω •́ )y");
    }

    /**
     * 查询所有角色(分页)
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selAllRole")
    public JsonResult selAllRole(Integer pageNum,Integer pageSize) {

        return roleService.selRole(pageNum,pageSize);
    }

    /**
     * 删除一个角色
     * @param id
     * @return
     */
    @GetMapping("/delRole")
    public JsonResult delRole(Integer id) {
        roleService.delRole(id);
        return new JsonResult(0,null ,"删除成功");
    }

    /**
     * 查询所有可选角色 不分页
     * @return
     */
    @GetMapping("/loadRole")
    public JsonResult loadRole() {
        List<Map> list = roleService.loadRole();
        return new JsonResult(0,list ,"可选角色");
    }
}