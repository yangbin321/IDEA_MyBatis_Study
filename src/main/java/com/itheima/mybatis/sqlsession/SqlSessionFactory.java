package com.itheima.mybatis.sqlsession;

/**
 * @author yangbin
 * @create 2019-09-29 20:48
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的sqlsession对象
     */
    SqlSession openSession();
}
