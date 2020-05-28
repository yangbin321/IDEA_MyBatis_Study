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

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author yangbin
 * @create 2019-09-28 12:16
 *  mybatis的curd
 *
 */
public class UserTest {

    /**
     *  将读取配置文件以及重复的操作定义成一个方法
     *  减少代码的重复
     */
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件,生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao接口的代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
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
     *     测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = iUserDao.findById(41);
        System.out.println(user1);

//        sqlSession.close();
//        //再次获取sqlSession对象
//        sqlSession = factory.openSession();

        sqlSession.clearCache();//次方法也可以清空缓存

        //重新获取这个对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
        User user2 = iUserDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache(){
        //1.根据id查询用户信息
        User user1 = iUserDao.findById(41);
        System.out.println(user1);
        //2.更新用户信息
        user1.setUsername("updata user clear cache");
        user1.setAddress("广西壮族自治区南宁市");
        iUserDao.updetaUser(user1);
        //3.再次查询id为41的用户
        User user2 = iUserDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
