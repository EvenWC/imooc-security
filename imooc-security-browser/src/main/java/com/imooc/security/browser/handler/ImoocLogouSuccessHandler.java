package com.imooc.security.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Administrator
 * @date: 2019/5/19 18:51
 * @description:
 */
public class ImoocLogouSuccessHandler  implements LogoutSuccessHandler {

    private SecurityProperties securityProperties;

    public ImoocLogouSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if(StringUtils.isEmpty(signOutUrl)){
            SimpleResponse simpleResponse = new SimpleResponse("退出登录成功");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));
        }else{
            response.sendRedirect(signOutUrl);
        }
    }
}
