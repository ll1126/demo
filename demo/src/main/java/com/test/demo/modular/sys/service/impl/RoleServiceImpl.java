package com.test.demo.modular.sys.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.modular.sys.entity.Role;
import com.test.demo.modular.sys.mapper.RoleMapper;
import com.test.demo.modular.sys.mapper.RoleMenuMapper;
import com.test.demo.modular.sys.service.RoleService;
import com.test.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加一个角色
     */
    @Transactional
    public String addRole(Role role, String checkMenu) {
        //添加角色信息
        role.setTdate(new Date());
        role.setTupdatedate(new Date());
        roleMapper.insertRole(role);

        //添加角色对应的菜单节点权限
        System.out.println(checkMenu);
        //选中菜单id 不为空时
        if(checkMenu!=null && !checkMenu.isEmpty()){
            String[] checks = checkMenu.split(",");
            Map<String, Object> parames = new HashMap<String, Object>();
            parames.put("roleId", role.getId());
            parames.put("menuIds", checks);
            roleMenuMapper.insertRoleMenu(parames);
        }
        return null;
    }

    /**
     * 查询所有角色
     * @return
     * @param pageNum
     * @param pageSize
     */
    public JsonResult selRole(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectRole();
        // 取分页信息
        PageInfo pageInfo = new PageInfo(list);
        Page page = PageUtils.getConvertPage(pageNum, pageSize, list, pageInfo);
        return new JsonResult(page);
    }

    /**
     * 删除一个角色
     * @param id
     */
    @Transactional
    public void delRole(Integer id) {
        //删除角色信息
        roleMapper.delRole(id);
        //删除角色和菜单关系
        roleMenuMapper.delRoleMenu(id);
    }

    /**
     * 加载所有可选角色
     * @return
     */
    public List<Map> loadRole() {
        return roleMapper.loadRole();
    }

}
