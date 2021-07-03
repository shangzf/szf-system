package com.shangzf.user.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SexEnum {

    MALE("M", "男性"),
    FEMALE("F", "女性"),
    UNKNOWN("U", "未知");

    @JsonValue
    private final String code;
    private final String name;

    SexEnum(String code, String name) {
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
