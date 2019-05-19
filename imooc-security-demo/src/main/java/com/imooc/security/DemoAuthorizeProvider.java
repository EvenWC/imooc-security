package com.imooc.security;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;
import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/5/19 16:16
 * @description:
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DemoAuthorizeProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if(StringUtils.isNotEmpty(signOutUrl)){
            config.antMatchers(signOutUrl).permitAll();
        }
        config.anyRequest()
                .access("@rbacSevice.hasPermission(request,authentication)");
    }
}
