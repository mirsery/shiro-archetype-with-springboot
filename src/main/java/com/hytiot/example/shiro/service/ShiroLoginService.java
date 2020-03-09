package com.hytiot.example.shiro.service;

import com.hytiot.example.shiro.entity.User;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:10
 **/
public interface ShiroLoginService {

    public User getUser(String username);

}