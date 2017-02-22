package com.iselin.authorization.resource.conf;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
            InsufficientAuthenticationException {

        System.out.println("CustomAccessDecisionManager.decide");

        if (null == configAttributes) {
            return;
        }

        Iterator<ConfigAttribute> cons = configAttributes.iterator();

        while(cons.hasNext()){
            ConfigAttribute ca = cons.next();
            String needRole = ca.getAttribute();
            //gra 为用户所被赋予的权限，needRole为访问相应的资源应具有的权限
            for (GrantedAuthority gra : authentication.getAuthorities()) {
                if (needRole.trim().equals(gra.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute arg0) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return true;
    }
}