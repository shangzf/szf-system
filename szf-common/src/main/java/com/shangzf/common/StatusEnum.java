package com.shangzf.common;

public enum StatusEnum {

    START("S", "创建"),
    UP("U", "上架"),
    DOWN("D", "下架");
    private String code;
    private String name;

    StatusEnum(final String code, final String name) {
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
