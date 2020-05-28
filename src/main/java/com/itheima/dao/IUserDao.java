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
     * 查询所有,同时获取到用户下所有账户的信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户id查询用户信息
     */
    User findById(Integer userId);

}
