package com.shangzf.front.user.code;

import com.shangzf.common.web.pojo.code.IResultCode;

public enum UserCode implements IResultCode {

    UNREGISTERED("601", "未注册");

    private String code;
    private String message;

    UserCode(String code, String message) {
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