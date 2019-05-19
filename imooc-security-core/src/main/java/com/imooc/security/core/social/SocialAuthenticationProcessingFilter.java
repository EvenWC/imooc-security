package com.imooc.security.core.social;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.social.security.SocialAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Administrator
 * @date: 2019/5/12 19:53
 * @description:
 */
public class SocialAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_OPENID_KEY = "openId";

    public static final String SPRING_SECURITY_FORM_PROVIDERID_KEY = "providerId";

    private String openIdParameter = SPRING_SECURITY_FORM_OPENID_KEY;

    private String providerIdParameter = SPRING_SECURITY_FORM_PROVIDERID_KEY;

    private boolean postOnly = true;

    protected SocialAuthenticationProcessingFilter() {
        super(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String openId = obtainOpenId(request);
        String providerId = obtainProviderId(request);

        if (openId == null) {
            openId = "";
        }

        if (providerId == null) {
            providerId = "";
        }

        openId = openId.trim();

        SocialAuthenticationToken authRequest = new SocialAuthenticationToken(
                openId, providerId);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    private String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }
    protected void setDetails(HttpServletRequest request,
                              SocialAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
