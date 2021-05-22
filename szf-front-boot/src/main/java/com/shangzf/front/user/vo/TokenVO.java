package com.shangzf.front.user.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TokenVO {

    @JsonIgnore
    private Integer code;
    @JsonIgnore
    private String message;

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "token_type")
    private String tokenType;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "expires_in")
    private Long expiresIn;
    private String scope;
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "user_name")
    private String username;
    private String jti;
}
