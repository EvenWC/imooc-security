package com.imooc.security;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.security.AccessControlContext;

/**
 * @author: Administrator
 * @date: 2019/5/19 09:09
 * @description:
 */
@Configuration
@EnableAuthorizationServer
public class SsoServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private AccessTokenConverter jwtAccessTokenConverter;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints.tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("client1Secret")
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("all")
                .and()
                .withClient("client2")
                .secret("client2Secret")
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("all");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.tokenKeyAccess("isAuthenticated()");
    }
}
