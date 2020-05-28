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

    /*
    查询所有操作
     */
    List<User> findAll();
}
