package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangbin
 * @create 2019-10-02 21:14
 */
public class Role implements Serializable {

    //使用java中的命名规范，在配置文件的时候需要注意使用怎样的配置文件（起别名）
    private Integer roleId;
    private String roleName;
    private String roleDesc;

    //多对多的关系映射，一个角色可以赋予多个用户
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
