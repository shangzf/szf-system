package com.shangzf.front.user.vo;

import com.shangzf.common.util.RegexUtil;
import com.shangzf.common.vo.request.RequestData;
import com.shangzf.front.user.vo.group.CodeGroup;
import com.shangzf.front.user.vo.group.PasswordGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginVO implements RequestData {

    private static final long serialVersionUID = 3008313784602265686L;
    @NotBlank(message = "手机号码不能为空", groups = {PasswordGroup.class, CodeGroup.class})
    @Pattern(regexp = RegexUtil.PHONEREGULAR, message = "非法的手机号", groups = {PasswordGroup.class, CodeGroup.class})
    private String phone;
    @NotBlank(message = "密码不能为空", groups = {PasswordGroup.class})
    private String password;
    @NotBlank(message = "验证码不能为空", groups = {CodeGroup.class})
    private String code;

    private String type;
}
