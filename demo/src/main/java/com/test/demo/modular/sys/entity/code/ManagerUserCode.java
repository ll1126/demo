package com.test.demo.modular.sys.entity.code;



import com.test.demo.modular.sys.entity.ManagerUser;

import java.util.Date;


/**
 * Title: ManagerUser
 * Description: 查询条件
 * Company: 
 * @author: LinLei
 * @date:   2018-07-05
 */
public class ManagerUserCode extends ManagerUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    public ManagerUserCode() {
    }

    /*
     *--------------------------------------------------
     * 常用自定义字段
     *--------------------------------------------------
     */
   
    /**
     * 排序
     */
     private String orderBy = null;
    
	 /**
     * 名字(全模糊)
     */
     private String managerNameLike;
     
    /**
     * 名字 (模糊查询%)
     */
     private String managerNameLeftLike;
     
    /**
     * %模糊查询 名字
     */
     private String managerNameRightLike;
     
     /**
     * 名字(不等于<>)
     */
     private String managerNameNotEqual;
     
	 /**
     * 手机号(全模糊)
     */
     private String managerPhoneLike;
     
    /**
     * 手机号 (模糊查询%)
     */
     private String managerPhoneLeftLike;
     
    /**
     * %模糊查询 手机号
     */
     private String managerPhoneRightLike;
     
     /**
     * 手机号(不等于<>)
     */
     private String managerPhoneNotEqual;
     
	 /**
     * 创建时间(起始日期)
     */
     private String createTimeBegin;
     
     /**
     * 创建时间(结束日期)
     */
     private String createTimeEnd;
     
	public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getOrderBy() {
        return orderBy;
    }
    
    public String getManagerNameLike() {
        return managerNameLike;
    }
    public void setManagerNameLike(String managerNameLike) {
        this.managerNameLike = managerNameLike;
    }
    
    public String getManagerNameLeftLike() {
        return managerNameLeftLike;
    }
    public void setManagerNameLeftLike(String managerNameLeftLike) {
        this.managerNameLeftLike = managerNameLeftLike;
    }
    
    public String getManagerNameRightLike() {
        return managerNameRightLike;
    }
    public void setManagerNameRightLike(String managerNameRightLike) {
        this.managerNameRightLike = managerNameRightLike;
    }
    
    public String getManagerNameNotEqual() {
        return managerNameNotEqual;
    }
    public void setManagerNameNotEqual(String managerNameNotEqual) {
        this.managerNameNotEqual = managerNameNotEqual;
    }
    
    public String getManagerPhoneLike() {
        return managerPhoneLike;
    }
    public void setManagerPhoneLike(String managerPhoneLike) {
        this.managerPhoneLike = managerPhoneLike;
    }
    
    public String getManagerPhoneLeftLike() {
        return managerPhoneLeftLike;
    }
    public void setManagerPhoneLeftLike(String managerPhoneLeftLike) {
        this.managerPhoneLeftLike = managerPhoneLeftLike;
    }
    
    public String getManagerPhoneRightLike() {
        return managerPhoneRightLike;
    }
    public void setManagerPhoneRightLike(String managerPhoneRightLike) {
        this.managerPhoneRightLike = managerPhoneRightLike;
    }
    
    public String getManagerPhoneNotEqual() {
        return managerPhoneNotEqual;
    }
    public void setManagerPhoneNotEqual(String managerPhoneNotEqual) {
        this.managerPhoneNotEqual = managerPhoneNotEqual;
    }
    
 	public String getCreateTimeBegin() {
        return createTimeBegin;
    }
    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }
    
    public String getCreateTimeEnd() {
        return createTimeEnd;
    }
    public void setCreateTimeEnd (String createTimeEnd ){
        this.createTimeEnd = createTimeEnd;
    }
    

}
