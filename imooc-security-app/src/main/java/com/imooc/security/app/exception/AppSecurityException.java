package com.imooc.security.app.exception;

/**
 * @author: Administrator
 * @date: 2019/5/18 09:52
 * @description:
 */
public class AppSecurityException extends RuntimeException {
    public AppSecurityException(String message){
        super(message);
    }
}
