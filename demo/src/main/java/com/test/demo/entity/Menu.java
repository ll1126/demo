package com.test.demo.entity;

public class Menu {

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }
}
