package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author yangbin
 * @create 2019-09-28 10:52
 *
 * 用户的持久层接口
 *
 */
public interface IUserDao {

    /**
     * 查询所有的方法
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户的方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户的方法
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户的方法
     */
    void deleteUser(Integer userId);//注意这里不要写User user，只要一个id就可以删除了

    /**
     * 根据用户id查询用户信息
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();



}
