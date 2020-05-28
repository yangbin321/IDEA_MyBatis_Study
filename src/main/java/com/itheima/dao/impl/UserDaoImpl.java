package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author yangbin
 * @create 2019-09-28 22:22
 */
public class UserDaoImpl implements IUserDao {

    //自己建
    private  SqlSessionFactory factory;

    //覆盖默认的工厂，然后使用的时候就会一直使用了
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll(){
        //1.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //2.使用session执行查询所有的方法
        /*
        session.selectList()里面放的就是配置文件中的唯一的
        <mapper namespace="com.itheima.dao.IUserDao">
            <!--配置查询所有 -->
            <select id="findAll" resultType="com.itheima.domain.User">
                select * from user
            </select>
        </mapper>

        使用namespace="com.itheima.dao.IUserDao"  + id="findAll" 即可找到唯一的方法。
         */
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");
        //记得关闭资源
        session.close();
        //3.返回查询的结果集
        return users;
    }
}
