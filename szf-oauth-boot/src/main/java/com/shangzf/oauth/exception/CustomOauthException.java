package com.shangzf.oauth.exception;

import com.shangzf.vo.response.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义权限异常处理
 */
public class CustomOauthException extends OAuth2Exception {

    private static final long serialVersionUID = 3422321015846542067L;
    private final ResponseData responseData;

    CustomOauthException(OAuth2Exception e){
        super(e.getSummary(), e);
        this.responseData = ResponseData.fail(AuthCodeEnum.valueOf(StringUtils.upperCase(e.getOAuth2ErrorCode())));
    }

}
