package com.hytiot.shiro.dao;

import com.hytiot.shiro.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: shiro-archetype-with-springboot
 * @description: 测试
 * @author: misery
 * @create: 2020-03-09 16:29
 **/
@SpringBootTest
public class ShiroDaoTest {


    @Autowired
    private ShiroUserDao shiroUserDao;

    @Test
    public void getUser() {
        User user = shiroUserDao.getUser("mirsery");
        System.out.println(user.getUsername() + "_" + user.getPassword());
    }

}
