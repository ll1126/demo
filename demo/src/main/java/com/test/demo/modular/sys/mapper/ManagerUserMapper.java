package com.test.demo.modular.sys.mapper;

import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.entity.code.ManagerUserCode;

import java.util.List;

/**
 * Title:ManagerUserService
 * Description:Map DAO层
 * Company:
 *
 * @author LinLei
 * @date 2018-07-05
 */
public interface ManagerUserMapper {
    /**
     * 新增一个用户
     *
     * @param managerUser
     */
    void getInsert(ManagerUser managerUser);

    /**
     * 删除一个用户
     *
     * @param id
     */
    void getDeleteById(Integer id);

    /**
     * 查询所有用户
     *
     * @param managerUserCode 查询条件
     * @return
     */
    List<ManagerUser> selAllManagerUser(ManagerUserCode managerUserCode);

    /**
     * 根据姓名查询用户
     *
     * @param managerName
     * @return
     */
    ManagerUser selUserBymanagerName(String managerName);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    ManagerUser selUserBymanagerId(String id);

    /**
     * 修改用户信息
     *
     * @param managerUser
     * @return
     */
    Integer updateManagerUser(ManagerUser managerUser);

}
