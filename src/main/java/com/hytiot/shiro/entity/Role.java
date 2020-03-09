package com.hytiot.shiro.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 13:36
 **/
public class Role implements Serializable {

    private String roleNo;
    private String roleName;
    private Set<Permission> permissions;

    public Role() {
    }

    public Role(String roleNo, String roleName, Set<Permission> permissions) {
        this.roleNo = roleNo;
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
