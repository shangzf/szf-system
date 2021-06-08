package com.shangzf.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义权限异常处理
 */
@JsonSerialize(using = CustomOAuthExceptionSerializer.class)
public class CustomOAuthException extends OAuth2Exception {

    private static final long serialVersionUID = 3422321015846542067L;
    private final ResultResponse resultResponse;

    CustomOAuthException(OAuth2Exception e) {
        super(e.getSummary(), e);
        this.resultResponse = ResultResponse.fail(AuthCodeEnum.valueOf(StringUtils.upperCase(e.getOAuth2ErrorCode())));
    }

    public ResultResponse getResponseData() {
        return resultResponse;
    }
}
