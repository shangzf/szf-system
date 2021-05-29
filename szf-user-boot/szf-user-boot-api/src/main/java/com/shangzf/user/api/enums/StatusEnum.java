package com.shangzf.user.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {

    DISABLE("D", "禁用"),
    ENABLE("E", "启用");

    @EnumValue
    @JsonValue
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
