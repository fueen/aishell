package com.ifueen.aishell.commons;

/**
 * 删除返回的数据
 */
public class ResultJson {
    //代表成功
    private Boolean sueccess = true;
    //报错的信息
    private String msg;

    public ResultJson() {
    }

    public ResultJson(Boolean sueccess, String msg) {
        this.sueccess = sueccess;
        this.msg = msg;
    }

    public Boolean getSueccess() {
        return sueccess;
    }

    public void setSueccess(Boolean sueccess) {
        this.sueccess = sueccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
