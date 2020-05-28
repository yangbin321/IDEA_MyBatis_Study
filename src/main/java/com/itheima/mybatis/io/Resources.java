package com.itheima.mybatis.io;

/**
 * @author yangbin
 * @create 2019-09-29 20:38
 */

import java.io.InputStream;

/**
    使用类加载器读取配置文件的内容
 */
public class Resources {

    /**
     *  根据传入的参数，获取一个字节的输入流
     */
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream("filePath");

    }
}
