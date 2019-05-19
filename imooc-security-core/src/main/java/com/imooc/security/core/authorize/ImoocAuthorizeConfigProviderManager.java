package com.imooc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @date: 2019/5/19 15:59
 * @description:
 */
@Component
public class ImoocAuthorizeConfigProviderManager implements AuthorizeConfigProviderManager{

    @Autowired
    private List<AuthorizeConfigProvider> providers = new ArrayList<>();

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        providers.forEach(provider->{
            provider.config(config);
        });
    }
}
