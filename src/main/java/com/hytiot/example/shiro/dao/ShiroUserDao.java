package com.hytiot.example.shiro.dao;

import com.hytiot.example.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 14:22
 **/
@Mapper
@Repository
public interface ShiroUserDao {

    public User getUser(String username);

}
