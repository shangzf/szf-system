package com.shangzf.front.user.dto;

import com.shangzf.common.util.RegexUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Schema(name = "登录")
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 3008313784602265686L;

    @Schema(name = "手机号码")
    @NotBlank(message = "手机号码不能为空", groups = {PasswordGroup.class, CodeGroup.class})
    @Pattern(regexp = RegexUtil.PHONEREGULAR, message = "非法的手机号", groups = {PasswordGroup.class, CodeGroup.class})
    private String phone;

    @Schema(name = "密码")
    @NotBlank(message = "密码不能为空", groups = {PasswordGroup.class})
    private String secret;

    @Schema(name = "验证码")
    @NotBlank(message = "验证码不能为空", groups = {CodeGroup.class})
    private String code;

    @Hidden
    private String type;

    public interface CodeGroup {
    }

    public interface PasswordGroup {
    }
}
