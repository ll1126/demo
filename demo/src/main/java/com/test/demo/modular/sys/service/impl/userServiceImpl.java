package com.test.demo.modular.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.demo.common.dto.Page;
import com.test.demo.common.dto.PageUtils;
import com.test.demo.modular.sys.entity.ManagerRole;
import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;
import com.test.demo.modular.sys.mapper.ManagerRoleMapper;
import com.test.demo.modular.sys.mapper.ManagerUserMapper;
import com.test.demo.modular.sys.service.userService;
import com.test.demo.util.JsonResult;
import com.test.demo.util.MD5Util;
import com.test.demo.util.easyExcel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private ManagerUserMapper managerUserMapper;
    @Autowired
    private ManagerRoleMapper managerRoleMapper;

    /**
     * 查询所有用户（分页）
     *
     * @param pageNum
     * @param pageSize
     * @param managerUserCode 查询条件
     * @return
     */
    public JsonResult selUserList(Integer pageNum, Integer pageSize, ManagerUserCode managerUserCode) {
        PageHelper.startPage(pageNum, pageSize);
        //查询所有符合条件的用户
        List<ManagerUser> list = managerUserMapper.selAllManagerUser(managerUserCode);
        // 取分页信息
        PageInfo pageInfo = new PageInfo(list);
        Page page = PageUtils.getConvertPage(pageNum, pageSize, list, pageInfo);
        return new JsonResult(page);
    }

    /**
     * 新增一个用户 / 修改已有用户
     *
     * @param managerUser
     * @param roleId
     * @param isUpdate 0: 新增  1：修改
     * @return
     */
    @Transactional
    public String insertUser(ManagerUser managerUser, Integer roleId,Integer isUpdate) {
        //新增用户

        if(isUpdate!=null && isUpdate == 0){
            ManagerUser managerUser1 = managerUserMapper.selUserBymanagerName(managerUser.getManagerName());
            if(managerUser1 != null){
                return "姓名重复，请加个标识区分";
            }
            //新增一个用户
            managerUser.setCreateTime(new Date());
            //默认密码 123456
            managerUser.setManagerPassword(MD5Util.getMD5("123456"));
            managerUserMapper.getInsert(managerUser);
            //新增一个用户与角色对应关系
            ManagerRole managerRole = new ManagerRole(managerUser.getId(),roleId);
            managerRoleMapper.getInsert(managerRole);
        }else{
            //修改已有用户信息
            //修改基本信息
            managerUserMapper.updateManagerUser(managerUser);
            if(roleId != null){
                //修改角色对应信息
                managerRoleMapper.updateByManagerId(managerUser.getId(),roleId);
            }


        }

        return null;
    }

    /**
     * 导出所有用户
     * @param response
     */
    public void downUser(HttpServletResponse response) {
        List<ManagerUser> list = managerUserMapper.selAllManagerUser(null);
        try {
            ExcelUtil.writeExcelWithSheets(response, list, "用户", "sheet1", new ManagerUser())
    //                .write(list, sheetName2, new ExportInfo())
    //                .write(list, sheetName3, new ExportInfo())
                    .finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
