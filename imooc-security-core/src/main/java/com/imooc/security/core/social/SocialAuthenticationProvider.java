package com.imooc.security.core.social;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @date: 2019/5/12 20:11
 * @description:
 */
public class SocialAuthenticationProvider implements AuthenticationProvider {


    private UsersConnectionRepository usersConnectionRepository;

    private SocialUserDetailsService socialUserDetailsService;

    public SocialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository,SocialUserDetailsService socialUserDetailsService) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.socialUserDetailsService = socialUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialAuthenticationToken socialAuthenticationToken = (SocialAuthenticationToken)authentication;
        String openId = (String)socialAuthenticationToken.getPrincipal();
        String providerId = socialAuthenticationToken.getProviderId();
        HashSet<String> providerUserIds = new HashSet<>();
        providerUserIds.add(openId);
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(providerId, providerUserIds);
        if(CollectionUtils.isEmpty(userIds) || userIds.size() != 1){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        String userId = userIds.iterator().next();
        SocialUserDetails socialUserDetails = socialUserDetailsService.loadUserByUserId(userId);
        SocialAuthenticationToken authenticationToken = new SocialAuthenticationToken(socialUserDetails.getAuthorities(), openId, providerId);
        authenticationToken.setDetails(authentication.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(SocialAuthenticationToken.class);
    }
}
