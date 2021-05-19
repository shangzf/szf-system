package com.shangzf.front.user.response;

import com.shangzf.common.vo.response.IResultCode;

public enum UserCode implements IResultCode {

    UNREGISTERED(201, "未注册");

    private int code;
    private String message;

    UserCode(int code, String message) {
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
