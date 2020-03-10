package com.hytiot.example.shiro.service;

import com.hytiot.example.shiro.entity.Permission;
import com.hytiot.example.shiro.entity.User;
import java.util.Set;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:10
 **/
public interface ShiroControlService {

    public User getUser(String username);

    public Set<Permission> getPermissions();

}