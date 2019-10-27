package com.ifueen.aishell.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

@ExcelTarget("emp")
public class EasyPOI {

    @Excel(name = "用户名")
    private String username;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "说明")
    private String speak;

    @Excel(name = "头像",type = 2,height = 50,width = 30)
    private String img;
    @Excel(name = "日期",format = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date date = new Date();

    @ExcelEntity
    private Dpt dpt;

    public Dpt getDpt() {
        return dpt;
    }

    public void setDpt(Dpt dpt) {
        this.dpt = dpt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
