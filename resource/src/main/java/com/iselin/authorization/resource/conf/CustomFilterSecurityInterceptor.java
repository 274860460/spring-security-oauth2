package com.iselin.authorization.resource.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private CustomInvocationSecurityMetadataSource securityMetadataSource;


//    @Resource
//    @Qualifier("customAccessDecisionManager")
//    @Override
//    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
//        super.setAccessDecisionManager(accessDecisionManager);
//    }

    @Autowired
    public void setAccessDecisionManager(CustomAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        infoke(fi);

    }

    private void infoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void destroy() {

    }


    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }

}