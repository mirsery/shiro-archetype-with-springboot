package com.hytiot.example.shiro.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-10 20:20
 **/
public class URLPathMatchingFilter extends PathMatchingFilter {

    /**
     * This default implementation always returns {@code true} and should be overridden by subclasses for custom logic
     * if necessary.
     *
     * @param request     the incoming ServletRequest
     * @param response    the outgoing ServletResponse
     * @param mappedValue the filter-specific config value mapped to this filter in the URL rules mappings.
     * @return {@code true} if the request should be able to continue, {@code false} if the filter will handle the
     * response directly.
     * @throws Exception if an error occurs
     * @see #isEnabled(ServletRequest, ServletResponse, String, Object)
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        String requestURL = getPathWithinApplication(request);
        System.out.println("requestURL: "+requestURL);

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return false;
        }
        try {
            subject.checkPermission(requestURL);
            return true;
        } catch (AuthorizationException e) {
            throw  new AuthorizationException("该用户没有相关的页面权限");
        }
    }
}