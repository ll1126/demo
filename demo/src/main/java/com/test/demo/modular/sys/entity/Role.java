package com.test.demo.modular.sys.entity;


import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title:Role
 * Description:实体类
 * Company:
 * Table: t_role
 * DataBase: user_power
 *
 * @author LinLei
 * @date 2018-07-02
 */
@Data
@Getter
@Setter
@ToString
public class Role implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 角色名字
     **/
    private String cname;
    /**
     * 角色描述
     **/
    private String cdesc;
    /**
     * 创建时间
     **/
    private Date tdate;
    /**
     * 状态 0：启用 1： 禁用
     **/
    private Integer fstate;
    /**
     * 最后修改时间
     **/
    private Date tupdatedate;
    /**
     * 对应的菜单id
     */
    private String menuData;

    public Role() {
    }

    public Role(Integer id, String cname, String cdesc, Date tdate, Integer fstate, Date tupdatedate, String menuData) {
        this.id = id;
        this.cname = cname;
        this.cdesc = cdesc;
        this.tdate = tdate;
        this.fstate = fstate;
        this.tupdatedate = tupdatedate;
        this.menuData = menuData;
    }
}
