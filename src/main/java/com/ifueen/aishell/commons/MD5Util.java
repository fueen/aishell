package com.ifueen.aishell.commons;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密工具
 */
public class MD5Util {

    //设置盐值,设置为常量,以后基本不会更改
    public static final String SATL = "ifueen";
    //设置迭代次数,设置为常量,以后基本不会更改
    public static final Integer HASHITERATIONS  = 10;

    public static String createMd5Str(String pwd){
        /**
         * 进行加密
         * 第一个参数：加密算法
         * 第二个参数：原密码
         * 第三个参数：盐值
         * 第四个参数：迭代次数
         */
        SimpleHash hash = new SimpleHash("MD5", pwd, SATL, HASHITERATIONS);

        return hash.toString();
    }

}
