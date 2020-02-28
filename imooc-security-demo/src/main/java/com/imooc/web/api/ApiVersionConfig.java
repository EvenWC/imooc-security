package com.imooc.web.api;

import org.springframework.boot.autoconfigure.web.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author WangCheng
 * @version $ Id: ApiVersionConfig.java, v0.1 2020/2/28 21:39 WangCheng Exp $$
 */
@Configuration
public class ApiVersionConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }

    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return null;
    }

    @Override
    public ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
        return null;
    }
}
