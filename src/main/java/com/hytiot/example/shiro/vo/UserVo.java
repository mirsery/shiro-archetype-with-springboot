package com.hytiot.example.shiro.vo;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 15:42
 **/
public class UserVo {
    private String username;
    private char[] password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
