package com.shangzf.user.vo.code;


import com.shangzf.common.web.pojo.vo.code.IResultCode;

public enum UserCode implements IResultCode {

    UNREGISTERED("601", "未注册"),

    USER_ID_BIND("602", "用户已绑定"),

    UNION_ID_BIND("603", "unionId已绑定");

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
