package com.imooc.security.app.social.process;

import com.imooc.security.core.social.ImoocSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/5/18 10:03
 * @description:
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName,"imoocSocialSecurityConfig")){
            ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer)bean;
            configurer.signupUrl("/imooc/signup");
        }
        return bean;
    }
}
