package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author yangbin
 * @create 2019-10-03 11:27
 *
 *   在mybatis中针对CRUD一共有四个注解
 *  @Select
 *  @Insert
 *  @Update
 *  @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer userId);//只需要传一个id的参数就好

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findById(Integer userId);

    /**
     * 根据用户名查寻客户信息
     * @param username
     * @return
     */
 //   @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%' ")
    //第二种写法,这样的写法在查询的时候就不用写%了
    List<User> findByUserName(String username);

    /**
     * 查询用户的总条数
     * @param
     * @return
     */
    @Select("select count(*) from user")
    int finfTotalUser();
}
