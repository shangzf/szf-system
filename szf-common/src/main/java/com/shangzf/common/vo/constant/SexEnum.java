package com.shangzf.common.vo.constant;

public enum SexEnum {

    MALE("M", "男性"),
    FEMALE("F", "女性"),
    UNKNOWN("U", "未知");

    private String code;
    private String name;

    SexEnum(final String code, final String name) {
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
