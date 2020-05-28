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
     * 测试保存用户的操作
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis last insertId");
        user.setAddress("广西桂平市");
        user.setSex("女");
        user.setBirthday(new Date());

        //使用代理对象执行“保存用户”的方法

        System.out.println("保存操作之前：" + user);

        userDao.saveUser(user);

        System.out.println("保存操作之后：" + user);

        //想正常保存提交的数据，要在后面添加如下代码
//        sqlSession.commit();
        //但是为了写代码简便，所以将这个代码放到释放资源的代码中去
    }

    /**
     * 测试修改用户的操作
     */
    @Test
    public void testUpdata(){
        User user = new User();
        user.setId(45);
        user.setUsername("mybatis updata user");
        user.setAddress("广西贵港市");
        user.setSex("女");
        user.setBirthday(new Date());

        //使用代理对象执行“更新用户”的方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){

        //执行“删除用户”的方法
        userDao.deleteUser(44);
    }

    /**
     * 测试根据用户id进行查询
     */
    @Test
    public void testFindById(){

        //测试“查询一个用户”的方法
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
     * 测试查询用户的总记录条数
     */
    @Test
    public void testFindTotal(){

       int count = userDao.findTotal();
        System.out.println(count);
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
}
