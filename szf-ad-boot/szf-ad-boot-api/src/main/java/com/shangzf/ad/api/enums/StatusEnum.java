package com.shangzf.ad.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {

    DOWN("D", "下架"),
    UP("U", "上架");

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
