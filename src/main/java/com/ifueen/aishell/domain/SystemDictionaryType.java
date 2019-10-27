package com.ifueen.aishell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "systemdictionarytype")
public class SystemDictionaryType extends BaseDomain{

    private String sn;

    @Override
    public String toString() {
        return "SystemDictionaryType{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //产品统一管理(想不出来名词了)
    private String name;
    

}
