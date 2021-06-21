package com.shangzf.oauth.exception;

import com.shangzf.common.web.pojo.code.IResultCode;

public enum AuthCodeEnum implements IResultCode {
    INVALID_REQUEST("801", "无效请求"),
    INVALID_CLIENT("802", "无效client_id"),
    INVALID_GRANT("803", "无效授权"),
    INVALID_SCOPE("804", "无效scope"),
    INVALID_TOKEN("805", "无效token"),
    INSUFFICIENT_SCOPE("810", "授权不足"),
    REDIRECT_URI_MISMATCH("820", "redirect url不匹配"),
    ACCESS_DENIED("830", "拒绝访问"),
    METHOD_NOT_ALLOWED("840", "不支持该方法"),
    SERVER_ERROR("850", "权限服务错误"),
    UNAUTHORIZED_CLIENT("860", "未授权客户端"),
    UNAUTHORIZED("861", "未授权"),
    UNSUPPORTED_RESPONSE_TYPE("870", "不支持的响应类型"),
    UNSUPPORTED_GRANT_TYPE("871", "不支持的授权类型"),
    ERROR_VERIFY_CODE("872", "验证码错误或已过期");

    private String code;
    private String message;

    AuthCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
