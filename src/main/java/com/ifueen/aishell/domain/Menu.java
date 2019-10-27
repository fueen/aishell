package com.ifueen.aishell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends BaseDomain {

    //菜单名称
    private String name;
    //菜单路径
    private String url;
    //图标
    private String icon;

    //配置多对一
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    /**
     * JPA 在双向映射时，会相互包含对方的实例，相互引用，造成递归迭代，堆栈溢出
     * 加上@JsonIgnore注解即可
     *
     */
    @JsonIgnore
    private Menu parent;

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    //@Transient 不让这个字段交给JPA管理，不和数据库产生关联
    @Transient
    private List<Menu> children = new ArrayList<>();

    public String getName() {
        return name;
    }
    public String getText() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                '}';
    }
}
