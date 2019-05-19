package com.imooc.security.app.social.process;

import com.imooc.security.core.social.process.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/5/17 23:42
 * @description:
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler imoocAppAuthenticationSuccessHandler;


    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(imoocAppAuthenticationSuccessHandler);
    }
}
