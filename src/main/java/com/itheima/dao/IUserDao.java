package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import javax.management.Query;
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
     * 根据用户id查询用户信息
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件查询
     * @param user 查询的条件，可能有用户名，也可能有性别，也可能有地址，也可能都有，也可能都没有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

}
