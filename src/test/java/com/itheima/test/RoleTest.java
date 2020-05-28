package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author yangbin
 * @create 2019-09-28 12:16
 *  mybatis的curd
 *
 */
public class RoleTest {

    /**
     *  将读取配置文件以及重复的操作定义成一个方法
     *  减少代码的重复
     */
    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件,生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao接口的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    /**
     * 第五步就是测试方法
     */

    /**
     * 释放资源
     */
    @After//用于在执行测试方法之后执行
    public void destroy() throws Exception{

        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    //测试方法
    /**
    查询所有
     */
    @Test
    public void testFindAll() {
       List<Role> roles = roleDao.findAll();
       for (com.itheima.domain.Role role : roles){
           System.out.println("-----每个用户的信息------");
           System.out.println(role);
           System.out.println(role.getUser());
       }
    }
}
