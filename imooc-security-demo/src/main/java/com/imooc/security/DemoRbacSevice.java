package com.imooc.security;

import com.imooc.security.core.authorize.RbacSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/5/19 17:04
 * @description:
 */
@Component("rbacSevice")
public class DemoRbacSevice implements RbacSevice {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            String username = userDetails.getUsername();
            //模拟加载的资源,这里资源简单处理为url 也可以抽象出来。
            List<String> resources = new ArrayList<>();
            //resources.add("/user/me");
            resources.add("/");
            for (String path :   resources) {
                if(antPathMatcher.match(path,request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
