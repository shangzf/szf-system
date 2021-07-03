package com.shangzf.boss.user.vo;

import com.shangzf.boss.user.vo.enums.SignEnum;
import com.shangzf.common.web.pojo.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO {

    private static final long serialVersionUID = -8854310993713039365L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户头像地址")
    private String portrait;

    @Schema(description = "注册手机")
    private String phone;

    @Schema(description = "用户密码(可以为空，支持只用验证码注册、登录)")
    private String secret;

    @Schema(description = "注册IP")
    private String regIp;

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

    @Schema(description = "描述")
    private String remark;

}
