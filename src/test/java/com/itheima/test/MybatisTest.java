package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author yangbin
 * @create 2019-09-28 12:16
 *  mybatis的curd
 *
 */
public class MybatisTest {

    /**
     *  将读取配置文件以及重复的操作定义成一个方法
     *  减少代码的重复
     */
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {

        //1.读取配置文件,生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);

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
    查询所有的操作
     */
    @Test
    public void testFindAll() {
        List<User> user1 = userDao.findAll();
        for(User user : user1){//使用增强for循环打印
            System.out.println(user);
        }
    }

    /**
     * 测试根据用户id进行查询
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(45);
        System.out.println(user);
    }

    /**
     * 测试根据用户名字进行模糊查询
     */
    @Test
    public void testFindByName(){

        List<User>  users = userDao.findByName("%王%");
        for( User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试根据QueryVo中的条件查询
     */
    @Test
    public void testFindByVo(){

        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        //二者关联起来
        vo.setUser(user);

        List<User>  users = userDao.findUserByVo(vo);
        for( User u : users){
            System.out.println(u);
        }
    }

    /**
     * 测试根据条件进行查询
     */
    @Test
    public void testFindUserByCondition(){
        User u = new User();
        u.setUsername("张三");
        u.setSex("女");

        List<User>  users = userDao.findUserByCondition(u);
        for( User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试foreach标签的使用
     */
    @Test
    public void testFindUserInIds(){

        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setIds(list);

        List<User>  users = userDao.findUserInIds(vo);
        for( User user : users){
            System.out.println(user);
        }
    }
}
