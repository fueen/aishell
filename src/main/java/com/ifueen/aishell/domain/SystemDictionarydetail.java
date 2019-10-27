package com.ifueen.aishell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "systemdictionarydetail")
public class SystemDictionarydetail extends BaseDomain{
    //品牌名称
    private String name;

    @ManyToOne
    @JoinColumn(name = "types_id")
    private SystemDictionaryType systemDictionaryType;

    @Override
    public String toString() {
        return "SystemDictionarydetail{" +
                "name='" + name + '\'' +
                ", systemDictionaryType=" + systemDictionaryType +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemDictionaryType getSystemDictionaryType() {
        return systemDictionaryType;
    }

    public void setSystemDictionaryType(SystemDictionaryType systemDictionaryType) {
        this.systemDictionaryType = systemDictionaryType;
    }
}
