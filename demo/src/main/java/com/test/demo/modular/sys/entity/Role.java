package com.test.demo.modular.sys.entity;



import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * Title:Role
 * Description:实体类
 * Company:
 * Table: t_role
 * DataBase: user_power
 * @author LinLei
 * @date 2018-07-02
 */
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
    public Role() {
    }

    public Role(Integer id, String cname, String cdesc, Date tdate, Integer fstate, Date tupdatedate) {
        this.id = id;
        this.cname = cname;
        this.cdesc = cdesc;
        this.tdate = tdate;
        this.fstate = fstate;
        this.tupdatedate = tupdatedate;
    }

    @ApiModelProperty(notes = "")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(notes = "角色名字")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @ApiModelProperty(notes = "角色描述")
    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    @ApiModelProperty(notes = "创建时间")
    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    @ApiModelProperty(notes = "状态 0：启用 1： 禁用")
    public Integer getFstate() {
        return fstate;
    }

    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    @ApiModelProperty(notes = "最后修改时间")
    public Date getTupdatedate() {
        return tupdatedate;
    }

    public void setTupdatedate(Date tupdatedate) {
        this.tupdatedate = tupdatedate;
    }

}
