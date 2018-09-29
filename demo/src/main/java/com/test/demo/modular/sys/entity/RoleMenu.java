package com.test.demo.modular.sys.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title:RoleMenu
 * Description:角色和菜单关系实体类
 * Company:
 * Table: t_role_menu
 * DataBase: user_power
 *
 * @author LinLei
 * @date 2018-07-02
 */

@Data
@Getter
@Setter
@ToString
public class RoleMenu implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     **/
    private Integer roleId;
    /**
     * 菜单id
     **/
    private Integer menuId;

    public RoleMenu() {
    }

    public RoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
