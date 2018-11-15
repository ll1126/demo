package com.test.demo.modular.sys.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title:ManagerRole
 * Description:实体类
 * Company:
 * Table: t_manager_role
 * DataBase: user_power
 * @author LinLei
 * @date 2018-07-05
 */
@Data
@Getter
@Setter
@ToString
public class ManagerRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 人员id
     **/
    private Integer managerid;
    /**
     * 角色id
     **/
    private Integer roleid;
    public ManagerRole() {
    }

    public ManagerRole(Integer managerid, Integer roleid) {
        this.managerid = managerid;
        this.roleid = roleid;
    }

}
