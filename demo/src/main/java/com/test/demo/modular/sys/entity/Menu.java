package com.test.demo.modular.sys.entity;


import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title:Menu
 * Description:菜单实体类
 * Company:
 * Table: t_menu
 * DataBase: user_power
 *
 * @author LinLei
 * @date 2018-06-28
 */

@Data
@Getter
@Setter
@ToString
public class Menu implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 菜单名字
     **/
    private String menuName;
    /**
     * 菜单路径
     **/
    private String menuUrl;
    /**
     * 0：页面  1：按钮
     **/
    private Integer isButton;
    /**
     * 父节点id
     **/
    private Integer fparentid;
    /**
     * 图标
     **/
    private String clconpic;
    /**
     * 排序
     **/
    private Integer fsort;
    /**
     * 状态 0：启用 1：禁用
     **/
    private Integer fstate;
    /**
     * 创建人名字
     **/
    private String ccreateuser;
    /**
     * 创建日期
     **/
    private Date tdate;
    /**
     * 是否有子类
     */
    private Integer hasChild;
    /**
     * 子类集合
     */
    private List<Map> child;

    public Menu() {
    }

    public Menu(Integer id, String menuName, String menuUrl, Integer isButton, Integer fparentid, String clconpic, Integer fsort, Integer fstate, String ccreateuser, Date tdate, Integer hasChild, List<Map> child) {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.isButton = isButton;
        this.fparentid = fparentid;
        this.clconpic = clconpic;
        this.fsort = fsort;
        this.fstate = fstate;
        this.ccreateuser = ccreateuser;
        this.tdate = tdate;
        this.hasChild = hasChild;
        this.child = child;
    }
}
