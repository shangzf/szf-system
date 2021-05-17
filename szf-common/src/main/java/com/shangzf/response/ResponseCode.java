package com.shangzf.response;

/**
 * 响应状态枚举
 *
 * @author zefeng.shang@changhong.com
 * @version 0.0.1
 * @since 0.0.1
 */
public enum ResponseCode {

    SUCCESS(200,"成功"),
    FAIL(100,"失败");

    private int code;
    private String message;

    ResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
