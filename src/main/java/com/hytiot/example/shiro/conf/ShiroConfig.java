package com.hytiot.example.shiro.conf;

import com.hytiot.example.shiro.filters.ResourceFilter;
import com.hytiot.example.shiro.filters.URLPathMatchingFilter;
import com.hytiot.example.shiro.realms.HytDefaultRealm;
import com.hytiot.example.shiro.session.dao.ShiroHytSession;
import com.hytiot.example.shiro.session.manager.RedisSessionDao;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 11:33
 **/
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisSessionDao redisSessionDao;

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public Realm getHytDefaultRealm() {
        return new HytDefaultRealm();
    }

    /**
     * security-manager
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getHytDefaultRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinition = new LinkedHashMap<>();
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("requestURL", new URLPathMatchingFilter());
        filtersMap.put("resource", new ResourceFilter());
        filterChainDefinition.put("/login", "anon");
//        filterChainDefinition.put("/**", "authc");
        filterChainDefinition.put("/**", "requestURL");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        shiroFilterFactoryBean.setFilters(filtersMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager shiroSession = new ShiroHytSession();
        shiroSession.setSessionDAO(redisSessionDao);
        return shiroSession;
    }


}