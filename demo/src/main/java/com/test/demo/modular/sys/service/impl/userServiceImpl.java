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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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
     * @param managerUser 用户实体类
     * @param roleId      角色id
     * @param isUpdate    true： 修改 false： 新增
     * @return
     */
    @Transactional
    public String insertUser(ManagerUser managerUser, Integer roleId, boolean isUpdate) {
        //新增用户

        if (!isUpdate) {
            ManagerUser managerUser1 = managerUserMapper.selUserBymanagerName(managerUser.getManagerName());
            if (managerUser1 != null) {
                return "姓名重复，请加个标识区分";
            }
            //新增一个用户
            managerUser.setCreateTime(new Date());
            //默认密码 123456
            managerUser.setManagerPassword(MD5Util.getMD5("123456"));
            managerUserMapper.getInsert(managerUser);
            //新增一个用户与角色对应关系
            ManagerRole managerRole = new ManagerRole(managerUser.getId(), roleId);
            managerRoleMapper.getInsert(managerRole);
        } else {
            //修改已有用户信息
            //修改基本信息
            managerUserMapper.updateManagerUser(managerUser);
            if (roleId != null) {
                //修改角色对应信息
                managerRoleMapper.updateByManagerId(managerUser.getId(), roleId);
            }
        }
        return null;
    }

    /**
     * 导出所有用户
     *
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

    /**
     * 删除用户
     *
     * @param id 要删除的用户id
     */
    public void delUser(Integer id) {
        managerUserMapper.getDeleteById(id);
    }

    /**
     * 修改密码
     *
     * @param user 当前登录的用户
     * @param oldPwd       旧密码
     * @param firstNewPwd  第一次新密码
     * @param secondNewPwd 第二次新密码
     * @return
     */
    @Override
    public String updatePwd(ManagerUser user, String oldPwd, String firstNewPwd, String secondNewPwd) {
        if (oldPwd == null || firstNewPwd == null || secondNewPwd == null) {
            return "参数不完整";
        }

        ManagerUser managerUser = managerUserMapper.selUserBymanagerId(String.valueOf(user.getId()));
        if (managerUser == null) {
            return "账号异常,请重新登陆";
        }
        if (!MD5Util.getMD5(oldPwd).equals(managerUser.getManagerPassword())) {
            return "原密码错误";
        }
        if (oldPwd.equals(firstNewPwd)) {
            return "新密码不能和原密码一样";
        }
        if (!firstNewPwd.equals(secondNewPwd)) {
            return "两次新密码不相同";
        }
        ManagerUser newManagerUser = new ManagerUser();
        newManagerUser.setId(user.getId());
        newManagerUser.setManagerPassword(MD5Util.getMD5(firstNewPwd));
        Integer res = managerUserMapper.updateManagerUser(newManagerUser);
        if (res == 0) {
            return "修改失败";
        }
        return null;

    }

    @Override
    public String asyncTest() throws ExecutionException, InterruptedException {
        // 建立一个线程池
        ExecutorService exc = Executors.newFixedThreadPool(3);
        // 存放返回的结果
        List<Future<String>> futures = new ArrayList<Future<String>>();
        Callable task1 = new Task1("token");
        Future<String> future = exc.submit(task1);
        futures.add(future);
        Callable task2 = new Task2();
        Future<String> future2 = exc.submit(task2);
        futures.add(future2);
        System.out.println("等待结果：");
        for(Future<String> future3: futures){
            System.out.println("结果-----"+future3.get());
        }
        System.out.println("都结束啦");
        exc.shutdown();
        return null;
    }






}
