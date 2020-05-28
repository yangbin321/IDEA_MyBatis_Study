package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    //为了不区分大小写以及别名可以查询的到，使用以下方法
    @Results(id = "userMap" ,value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            //封装account对象，实现一对多,accounts就是user中定义封装的类
            @Result(property = "accounts",column = "id",
                    many = @Many(select="com.itheima.dao.IAccountDao.findAccountById",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap(value = "userMap")
    User findById(Integer userId);

    /**
     * 模糊客户信息
     * @param username
     * @return
     */
    //这种方法最常用
    @Select("select * from user where username like #{username}")
    @ResultMap(value = "userMap")
    List<User> findByUserName(String username);

}
