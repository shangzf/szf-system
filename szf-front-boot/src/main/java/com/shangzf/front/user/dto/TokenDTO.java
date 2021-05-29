package com.shangzf.front.user.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(name = "Token数据")
@Data
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -9128517552939499268L;
    @Hidden
    @JsonIgnore
    private String code;
    @Hidden
    @JsonIgnore
    private String message;

    @Schema(name = "授权Token")
    @JSONField(name = "access_token")
    private String accessToken;

    @Schema(name = "Token类型")
    @JSONField(name = "token_type")
    private String tokenType;

    @Schema(name = "刷新Token")
    @JSONField(name = "refresh_token")
    private String refreshToken;

    @Schema(name = "有效期(单位:秒)")
    @JSONField(name = "expires_in")
    private Long expiresIn;

    @Schema(name = "有效期(单位:秒)")
    private String scope;

    @Schema(name = "用户ID")
    @JSONField(name = "user_id")
    private Long userId;

    @Schema(name = "用户名")
    @JSONField(name = "user_name")
    private String username;

    @Schema(name = "唯一身份标识")
    private String jti;
}
