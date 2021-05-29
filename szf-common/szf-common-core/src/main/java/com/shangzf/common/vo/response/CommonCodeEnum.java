package com.shangzf.common.vo.response;

/**
 * 公共状态码枚举
 */
public enum CommonCodeEnum implements IResultCode {
    SUCCESS(200, "操作成功！"),
    SERVER_ERROR(500, "抱歉，系统繁忙，请稍后再试！"),
    FAIL(201, "操作失败！");

    private int code;
    private String message;

    CommonCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
