package com.shangzf.common;

public enum DeleteEnum {
    YES("Y", "是"),
    NO("N", "否");
    private String code;
    private String name;

    DeleteEnum(final String code, final String name) {
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
