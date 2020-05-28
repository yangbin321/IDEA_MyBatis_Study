package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
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
 * @create 2019-10-03 13:15
 */
public class AccountAnnotationCRUDTest {

    /**
     * 将变量定义在外部
     */
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao iAccountDao;

    /**
     * 定义执行测试前的操作
     */
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        iAccountDao = sqlSession.getMapper(IAccountDao.class);
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
     * 查询所有的操作
     */
    @Test
    public void testFindAll(){
        List<Account> accounts = iAccountDao.findAll();
        for (Account account : accounts){
            System.out.println("----------每个账户的用户信息----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
        System.out.println("查询成功");
    }
}
