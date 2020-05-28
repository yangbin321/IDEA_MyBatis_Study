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
 * @author yangbin
 * @create 2019-10-03 23:09
 */
public class SecondLevelCatchTest {

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
        in.close();
    }

    /**
     * 测试根据用户id查询用户的操作
     */
    @Test
    public void testFindById(){

        User user1 = userDao.findById(46);
        System.out.println(user1);
        sqlSession.close();//释放一级缓存

        sqlSession = factory.openSession();//再次打开sqlSession
        userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findById(46);
        System.out.println(user2);
        sqlSession.close();

        System.out.println(user1==user2);

        System.out.println("成功查询到客户信息");
    }


}
