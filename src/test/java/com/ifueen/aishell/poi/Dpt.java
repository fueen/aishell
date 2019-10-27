package com.ifueen.aishell.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;


@ExcelTarget("dept")
public class Dpt {

    @Excel(name = "名称_dept,部门名称_emp")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Excel(name = "部门地址_dept")
    private String address = "珠穆朗玛峰";

}
