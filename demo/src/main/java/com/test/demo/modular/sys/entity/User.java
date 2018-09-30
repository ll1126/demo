package com.test.demo.modular.sys.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Data
@Getter
@Setter
@ToString
public class User extends BaseRowModel {
    @ExcelProperty(value = "id" ,index = 0)
    private Integer id;
    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名" ,index = 1)
    private String username;
    /**
     * 密码
     */
    @ExcelProperty(value = "密码" ,index = 2)
    private String password;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间" ,index = 3)
    private Date createTime;

    public User() {

    }

    public User(Integer id, String username, String password, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }
}
