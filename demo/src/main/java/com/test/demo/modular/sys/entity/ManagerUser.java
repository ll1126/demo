package com.test.demo.modular.sys.entity;


import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title:ManagerUser
 * Description:实体类
 * Company:
 * Table: t_manager_user
 * DataBase: user_power
 *
 * @author LinLei
 * @date 2018-07-05
 */
@Data
@Getter
@Setter
@ToString
public class ManagerUser extends BaseRowModel implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @ExcelProperty(value = "id", index = 0)  //导出Excel用
    private Integer id;
    /**
     * 名字
     **/
    @ExcelProperty(value = "名字", index = 1)
    private String managerName;
    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 2)
    private String managerPassword;
    /**
     * 手机号
     **/
    @ExcelProperty(value = "手机号", index = 3)
    private String managerPhone;
    /**
     * 0: 男 1： 女
     **/
    @ExcelProperty(value = "性别 0: 男 1： 女", index = 4)
    private Integer managerSex;
    /**
     * 0: 启用  1： 禁用
     **/
    @ExcelProperty(value = "是否禁用 0: 启用  1： 禁用", index = 5)
    private Integer state;
    /**
     * 创建时间
     **/
    @ExcelProperty(value = "创建时间", index = 6)
    private Date createTime;
    /**
     * 角色名称
     */
    @ExcelProperty(value = "角色名称", index = 7)
    private String roleName;
    /**
     * 角色id
     */
    private Integer roleId;

    public ManagerUser() {
    }

    public ManagerUser(Integer id, String managerName, String managerPassword, String managerPhone, Integer managerSex, Integer state, Date createTime, String roleName, Integer roleId) {
        this.id = id;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
        this.managerPhone = managerPhone;
        this.managerSex = managerSex;
        this.state = state;
        this.createTime = createTime;
        this.roleName = roleName;
        this.roleId = roleId;
    }
}
