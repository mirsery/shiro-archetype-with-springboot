package com.hytiot.example.shiro.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.servlet.AdviceFilter;

/**
 * @program: community
 * @description: 资源权限过滤
 * @author: misery
 * @create: 2020-03-13 09:32
 **/
public class ResourceFilter extends AdviceFilter {

    /**
     * Returns {@code true} if the filter chain should be allowed to continue, {@code false} otherwise. It is called
     * before the chain is actually consulted/executed.
     * <p/>
     * The default implementation returns {@code true} always and exists as a template method for subclasses.
     *
     * @param request  the incoming ServletRequest
     * @param response the outgoing ServletResponse
     * @return {@code true} if the filter chain should be allowed to continue, {@code false} otherwise.
     * @throws Exception if there is any error.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        System.out.println("resource filter");

        return true;
    }

}
