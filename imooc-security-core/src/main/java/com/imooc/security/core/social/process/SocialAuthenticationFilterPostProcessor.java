package com.imooc.security.core.social.process;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 社交登录后置处理器
 * @author: Administrator
 * @date: 2019/5/17 23:36
 * @description:
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter filter);
}
