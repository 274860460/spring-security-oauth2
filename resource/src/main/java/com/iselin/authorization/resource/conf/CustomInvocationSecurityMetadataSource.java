package com.iselin.authorization.resource.conf;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    private HashMap<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

    @PostConstruct
    public void init() {

        Collection<ConfigAttribute> objects;
        ConfigAttribute ca1;

        // c
        objects = new HashSet<>();
        ca1 = new SecurityConfig("ROLE_CREDIT");
        objects.add(ca1);
        resourceMap.put("/c", objects);
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        init();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();


//        SecurityContextHolder.getContext().getAuthentication().getAuthorities()

        // 返回当前 url  所需要的权限
        return resourceMap.get(requestUrl);
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}