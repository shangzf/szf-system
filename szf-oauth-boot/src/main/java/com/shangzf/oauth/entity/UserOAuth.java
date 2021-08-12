package com.shangzf.oauth.entity;

import com.shangzf.user.api.dto.enums.SignEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户")
public class UserOAuth implements Serializable {

    private static final long serialVersionUID = 5177881545003968555L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户密码(可以为空，支持只用验证码注册、登录)")
    private String secret;

    @Schema(description = "是否有效用户")
    private Boolean accountNonExpired;

    @Schema(description = "账号是否未过期")
    private Boolean credentialsNonExpired;

    @Schema(description = "是否未锁定")
    private Boolean accountNonLocked;

    @Schema(description = "用户状态：E-能登录，D-不能登录", allowableValues = {"E", "D"})
    private SignEnum sign;

    @Schema(description = "是否删除")
    private Boolean deleted;

}