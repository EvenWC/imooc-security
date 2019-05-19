package com.imooc.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Administrator
 * @date: 2019/5/12 11:18
 * @description:
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     */
    String saveValidateCode(ServletWebRequest request,ValidateCodeType validateCodeType, ValidateCode code);

    /**
     * 获取验证码
     */
    ValidateCode getValidateCode(ServletWebRequest request,ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     */
    void removeValidateCode(ServletWebRequest request,ValidateCodeType validateCodeType);

}
