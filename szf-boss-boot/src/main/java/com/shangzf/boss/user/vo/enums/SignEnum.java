package com.shangzf.boss.user.vo.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SignEnum {

    DISABLE("D", "禁用"),
    @JsonEnumDefaultValue
    ENABLE("E", "启用");

    private final String code;
    private final String name;

    SignEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
