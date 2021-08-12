package com.shangzf.user.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SignEnum {

    DISABLE("D", "禁用"),
    ENABLE("E", "启用");

    @JsonValue
    private final String code;
    private final String name;

    SignEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SignEnum create(String value){
        try{
            return SignEnum.valueOf(value);
        }catch (IllegalArgumentException e){
            for (SignEnum signEnum : SignEnum.values()) {
                if (signEnum.code.equals(value)) {
                    return signEnum;
                }
            }
            throw new IllegalArgumentException("No element matches "+value);
        }
    }
}
