package com.shangzf.front.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(name = "Token数据")
@Data
public class TokenVO implements Serializable {

    private static final long serialVersionUID = -9128517552939499268L;

    @Schema(name = "授权Token")
    private String accessToken;

    @Schema(name = "Token类型")
    private String tokenType;

    @Schema(name = "刷新Token")
    private String refreshToken;

    @Schema(name = "有效期(单位:秒)")
    private Long expiresIn;

    @Schema(name = "有效期(单位:秒)")
    private String scope;

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "唯一身份标识")
    private String jti;
}
