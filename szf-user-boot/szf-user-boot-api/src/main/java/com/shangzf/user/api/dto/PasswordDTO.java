package com.shangzf.user.api.dto;

import com.shangzf.common.constant.RegexConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Schema(description = "密码")
@Data
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = -3798546859599661988L;

    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegexConstant.PASSWORD, message = "正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线。")
    private String password;

    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String configPassword;
}
