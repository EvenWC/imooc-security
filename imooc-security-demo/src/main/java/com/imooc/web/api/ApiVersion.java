package com.imooc.web.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author WangCheng
 * @version $ Id: ApiVersion.java, v0.1 2020/2/28 21:24 WangCheng Exp $$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiVersion {

    /**
     * 版本号
     * @return
     */
    double value() default 1.0;
}
