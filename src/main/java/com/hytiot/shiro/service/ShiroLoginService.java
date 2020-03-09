package com.hytiot.shiro.service;

import com.hytiot.shiro.entity.User;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:10
 **/
public interface ShiroLoginService {

    public User getUser(String username);

}