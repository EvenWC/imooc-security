package com.imooc.security.core.authorize;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Administrator
 * @date: 2019/5/19 17:02
 * @description:
 */
public interface RbacSevice {

    boolean hasPermission(HttpServletRequest request,Authentication authentication);

}
