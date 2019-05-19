package com.imooc.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/5/14 22:05
 * @description:
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private SocialUserDetailsService socialUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SocialAuthenticationProcessingFilter processingFilter = new SocialAuthenticationProcessingFilter();
        processingFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        processingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        processingFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        SocialAuthenticationProvider provider = new SocialAuthenticationProvider(usersConnectionRepository,socialUserDetailsService);
        http.authenticationProvider(provider)
                .addFilterBefore(processingFilter,UsernamePasswordAuthenticationFilter.class);
    }

}
