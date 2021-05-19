package com.shangzf.oauth.exception;

import com.shangzf.vo.response.IResultCode;

public enum AuthCodeEnum implements IResultCode {
    INVALID_REQUEST(401, "无效请求"),
    INVALID_CLIENT(402, "无效client_id"),
    INVALID_GRANT(403, "无效授权"),
    INVALID_SCOPE(404, "无效scope"),
    INVALID_TOKEN(405, "无效token"),
    INSUFFICIENT_SCOPE(410, "授权不足"),
    REDIRECT_URI_MISMATCH(420, "redirect url不匹配"),
    ACCESS_DENIED(430, "拒绝访问"),
    METHOD_NOT_ALLOWED(440, "不支持该方法"),
    SERVER_ERROR(450, "权限服务错误"),
    UNAUTHORIZED_CLIENT(460, "未授权客户端"),
    UNAUTHORIZED(461, "未授权"),
    UNSUPPORTED_RESPONSE_TYPE(470, "不支持的响应类型"),
    UNSUPPORTED_GRANT_TYPE(471, "不支持的授权类型"),
    ERROR_VERIFY_CODE(472, "验证码错误或已过期");

    private int code;
    private String message;

    AuthCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
