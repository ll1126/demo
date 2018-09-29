package com.test.demo.modular.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.core.auth.ATTUser;
import com.test.demo.modular.sys.entity.Menu;
import com.test.demo.modular.sys.mapper.MenuMapper;
import com.test.demo.modular.sys.service.MenuService;
import com.test.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 读取用户的菜单 （目前还是全部菜单，权限还没加）
     *
     * @return
     * @param request
     */
    public JsonResult selMenu(HttpServletRequest request) {
        //从token里拿userid
        String roleId = String.valueOf(request.getAttribute(ATTUser.ROLE_TOKEN));
        System.out.println("roleId:"+ roleId);
        List<Map> list = menuMapper.selMenu(Integer.valueOf(roleId));
        return new JsonResult(list);
    }

    /**
     * 创建一个菜单
     *
     * @param menu
     * @return
     */
    public void insertMenu(Menu menu) {
        menu.setTdate(new Date());
        menuMapper.getInsert(menu);
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
