package com.imooc.security.core.properties;

/**
 * @author: Administrator
 * @date: 2019/5/18 16:18
 * @description:
 */
public class Oauth2Propertites {

    private Oauth2ClientProperties[] oauth2ClientProperties = {};

    private String jwtSigningKey = "imooc";

    public Oauth2ClientProperties[] getOauth2ClientProperties() {
        return oauth2ClientProperties;
    }

    public void setOauth2ClientProperties(Oauth2ClientProperties[] oauth2ClientProperties) {
        this.oauth2ClientProperties = oauth2ClientProperties;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }
}
