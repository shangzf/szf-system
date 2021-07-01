package com.shangzf.authority.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "请求用户是否有权限访问")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthorityExtDTO extends AuthorityDTO {
    private static final long serialVersionUID = 1754480565075611438L;

    @Schema(description = "请求的用户TOKEN")
    @NotBlank(message = "请求的用户TOKEN")
    private String authentication;
}
