package com.imooc.security.browser.validate.code;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeRepository;
import com.imooc.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Administrator
 * @date: 2019/5/12 14:04
 * @description:
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 验证码放入session时的前缀
     */
    private static final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private String keyPrefix = SESSION_KEY_PREFIX;

    @Override
    public String saveValidateCode(ServletWebRequest request, ValidateCodeType validateCodeType, ValidateCode code) {
        String key = generateValidateCodeKey(keyPrefix,request,validateCodeType);
        sessionStrategy.setAttribute(request,key,code);
        return key;
    }

    @Override
    public ValidateCode getValidateCode(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode)sessionStrategy.getAttribute(request,generateValidateCodeKey(keyPrefix,request,validateCodeType));
    }

    @Override
    public void removeValidateCode(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request,generateValidateCodeKey(keyPrefix,request,validateCodeType));
    }
    /**
     * 生成validate code key
     */
    private String generateValidateCodeKey(String keyPrefix,ServletWebRequest request ,ValidateCodeType validateCodeType){
        return keyPrefix + validateCodeType.toString().toUpperCase();
    }
}
