package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author yangbin
 * @create 2019-10-03 21:54
 */
public interface IAccountDao {

    /**
     * 查询所有账户，并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    //封装account的数据
    @Results(id = "accounrMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            //封装user，其中user是封装对象，
            @Result(property = "user",column = "uid",
                    one = @One(select="com.itheima.dao.IUserDao.findById",
                            fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 根据用户查询账户信息
     * @param uid
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    List<Account> findAccountById(Integer uid);
}
