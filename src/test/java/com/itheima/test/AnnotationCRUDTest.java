package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author yangbin
 * @create 2019-10-03 13:15
 */
public class AnnotationCRUDTest {

    /**
     * 将变量定义在外部
     */
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 定义执行测试前的操作
     */
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 定义执行测试后的操作
     */
    @After
    public void destory() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    /**
     * 测试保存用户的操作
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("saveUser mybatis annotation");
        user.setAddress("广西白色市");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    /**
     * 测试修改用户的操作
     */
    @Test
    public void tsetUpdateUser(){
        User user = new User();
        user.setId(48);
        user.setUsername("刘德华");
        user.setAddress("中国香港");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
        System.out.println("成功更新");
    }

    /**
     * 测试删除用户的操作
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(48);
        System.out.println("删除成功");
    }

    /**
     * 测试根据用户id查询用户的操作
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(46);
        System.out.println(user);
        System.out.println("成功查询到客户信息");
    }

    /**
     * 测试——模糊——查询用户的操作
     */
    @Test
    public void testFindByUserName(){
        //第一种写法
//        List<User> users =  userDao.findByUserName("%mybatis%");
        //第二种写法，这样的写法在查询的时候不用写%了
        List<User> users =  userDao.findByUserName("mybatis");
        for (User user : users){
            System.out.println(user);
        }
        System.out.println("成功查询到客户信息");
    }

    /**
     * 查询总记录条数
     */
    @Test
    public void testFindTotalUser(){
        int total = userDao.finfTotalUser();
        System.out.println("总记录条数：" + total);

    }

}
