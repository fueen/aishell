package com.ifueen.aishell.domain;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission extends BaseDomain{
    //权限名称
    private String name;
    //资源地址
    private String url;
    //描述
    private String descs;
    //角色
    private String sn;
    /**
     * 多对一,对应菜单编号
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
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

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", descs='" + descs + '\'' +
                ", sn='" + sn + '\'' +
                ", id=" + id +
                '}';
    }
}
