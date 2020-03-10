package com.hytiot.example.shiro.service.impl;

import com.hytiot.example.shiro.dao.ShiroUserDao;
import com.hytiot.example.shiro.entity.Permission;
import com.hytiot.example.shiro.entity.User;
import com.hytiot.example.shiro.service.ShiroControlService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:13
 **/
@Service
public class ShiroControlServiceImpl implements ShiroControlService {

    @Autowired
    private ShiroUserDao shiroUserDao;

    @Override
    public User getUser(String username) {
        return shiroUserDao.getUser(username);
    }

    @Override
    public Set<Permission> getPermissions() {
        return null;
    }
}
