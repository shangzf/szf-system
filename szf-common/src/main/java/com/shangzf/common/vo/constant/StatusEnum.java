package com.shangzf.common.vo.constant;

public enum StatusEnum {

    CREATE("C", "创建"),
    DISABLE("D", "禁用"),
    ENABLE("E", "启用"),
    UP("U", "上架");
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
