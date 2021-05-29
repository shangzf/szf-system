package com.shangzf.common.pojo.vo.code;

/**
 * 公共状态码枚举
 */
public enum CommonCodeEnum implements IResultCode {
    SUCCESS("200", "请求成功"),
    FAIL("400", "请求失败"),
    UNAUTHORIZED("401", "未授权"),
    NO_ACCESS("403", "禁止访问"),
    NOT_FOUND("404", "未找到"),
    METHOD_NOT_SUPPORTED("405", "方法不支持"),
    TIME_OUT("408", "超时"),
    MEDIA_NOT_SUPPORTED("415", "媒体格式不支持"),
    TYPE_MISMATCH("430","类型不匹配"),
    SERVER_EXCEPTION("500", "服务器异常"),
    GATEWAY_INVALID("502", "错误网关"),
    SERVICE_NOT_AVAILABLE("503", "服务不可用"),
    GATEWAY_TIME_OUT("504", "网关超时"),
    NOT_SUPPORTED_HTTP("505", "服务器不支持请求的HTTP协议的版本");

    private String code;
    private String message;

    CommonCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
