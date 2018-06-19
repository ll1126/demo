package com.test.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
public class Menu implements Serializable{



    private Integer id;
    /**
     * 菜单名字
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String menuUrl;

    public Menu() {
    }

    public Menu(Integer id, String menuName, String menuUrl) {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

}
