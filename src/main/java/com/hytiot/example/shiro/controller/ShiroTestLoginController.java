package com.hytiot.example.shiro.controller;

import com.hytiot.example.shiro.vo.UserVo;
import java.io.Serializable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: shiro-archetype-with-springboot
 * @description: 示例测试类、默认注释
 * @author: misery
 * @create: 2020-03-09 14:02
 **/
@RestController
public class ShiroTestLoginController {

    @PostMapping("/login")
    public String login(@ModelAttribute UserVo user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            Serializable tokenId = subject.getSession().getId();
            return tokenId.toString();
        } catch (AuthenticationException e) {
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            return "没有权限";
        }
    }

    @PostMapping("/index")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session.getId().toString();
    }

    @PostMapping("/check")
    public String check() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session.getId().toString();
    }


}
