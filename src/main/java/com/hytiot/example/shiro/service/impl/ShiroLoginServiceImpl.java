package com.hytiot.example.shiro.service.impl;

import com.hytiot.example.shiro.dao.ShiroUserDao;
import com.hytiot.example.shiro.entity.User;
import com.hytiot.example.shiro.service.ShiroLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:13
 **/
@Service
public class ShiroLoginServiceImpl implements ShiroLoginService {

    @Autowired
    private ShiroUserDao shiroUserDao;

    @Override
    public User getUser(String username) {
        return shiroUserDao.getUser(username);
    }
}
