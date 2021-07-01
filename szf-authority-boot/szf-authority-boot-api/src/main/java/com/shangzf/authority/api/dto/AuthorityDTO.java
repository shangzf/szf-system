package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Schema(description = "请求用户是否有权限访问")
@Data
@NoArgsConstructor
public class AuthorityDTO implements Serializable {

    private static final long serialVersionUID = -3496078086261037631L;

    @Schema(description = "请求的用户ID")
    @NotBlank(message = "请求的用户ID")
    private String userId;

    @Schema(description = "请求的HTTP地址")
    @NotBlank(message = "请求的HTTP地址")
    private String url;

    @Schema(description = "请求的HTTP方法")
    @NotBlank(message = "请求的HTTP方法")
    private String method;
}
