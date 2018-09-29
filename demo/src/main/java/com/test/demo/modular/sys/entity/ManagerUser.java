package com.test.demo.modular.sys.entity;



import java.util.Date;
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
 * @author LinLei
 * @date 2018-07-05
 */
@Data
@Getter
@Setter
@ToString
public class ManagerUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 名字
     **/
    private String managerName;
    /**
     * 密码
     */
    private String managerPassword;
    /**
     * 手机号
     **/
    private String managerPhone;
    /**
     * 0: 男 1： 女
     **/
    private Integer managerSex;
    /**
     * 0: 启用  1： 禁用
     **/
    private Integer state;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 角色名称
     */
    private String roleName;

    public ManagerUser() {
    }

    public ManagerUser(Integer id, String managerName, String managerPassword, String managerPhone, Integer managerSex, Integer state, Date createTime, String roleName) {
        this.id = id;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
        this.managerPhone = managerPhone;
        this.managerSex = managerSex;
        this.state = state;
        this.createTime = createTime;
        this.roleName = roleName;
    }
}
