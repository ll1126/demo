package com.test.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Data
@Getter
@Setter
@ToString
public class User {

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码aa
     */
    private String password;
    /**
     * 创建时间
     */
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
