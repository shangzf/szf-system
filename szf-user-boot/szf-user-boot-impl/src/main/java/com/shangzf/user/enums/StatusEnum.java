package com.shangzf.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum StatusEnum {

    DISABLE("D", "禁用"),
    ENABLE("E", "启用");

    @EnumValue
    private final String code;
    private final String name;

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
