package com.hytiot.shiro.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 13:36
 **/
public class User implements Serializable {

    private String userNo;
    private String username;
    private String password;
    private Set<Role> roles;

    public User() {
    }

    public User(String userNo, String username, String password, Set<Role> roles) {
        this.userNo = userNo;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}