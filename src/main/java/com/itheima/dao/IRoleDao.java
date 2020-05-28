package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @author yangbin
 * @create 2019-10-02 20:43
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
