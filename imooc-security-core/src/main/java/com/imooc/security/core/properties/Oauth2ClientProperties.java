package com.imooc.security.core.properties;

/**
 * @author: Administrator
 * @date: 2019/5/18 16:16
 * @description:
 */
public class Oauth2ClientProperties {

    private String clientId;

    private String clentSecurity;

    private int accessTokenValiditySeconds = 3600;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClentSecurity() {
        return clentSecurity;
    }

    public void setClentSecurity(String clentSecurity) {
        this.clentSecurity = clentSecurity;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
}
