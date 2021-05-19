package com.shangzf.oauth.exception;

import com.shangzf.vo.response.ResponseData;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义权限异常处理
 */
public class CustomOauthException extends OAuth2Exception {

    private ResponseData responseData;

    CustomOauthException(OAuth2Exception e){
        super(e.getSummary(),e);
        this.responseData = ResponseData.fail();
    }
}
