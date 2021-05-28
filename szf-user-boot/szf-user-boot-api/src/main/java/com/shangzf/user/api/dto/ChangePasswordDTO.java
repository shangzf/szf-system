package com.shangzf.user.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Schema(name = "修改密码")
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangePasswordDTO extends PasswordDTO {
    private static final long serialVersionUID = -8572932470233945854L;

    @Schema(name = "原密码")
    @NotBlank(message = "原密码不能为空")
    private String sourcePassword;
}
