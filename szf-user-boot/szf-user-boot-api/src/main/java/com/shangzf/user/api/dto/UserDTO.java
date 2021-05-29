package com.shangzf.user.api.dto;

import com.shangzf.user.api.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Schema(name = "用户")
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 5177881545003968555L;

    @Schema(name = "用户ID")
    private Long id;

    @Schema(name = "用户昵称")
    private String name;

    @Schema(name = "用户头像地址")
    private String portrait;

    @Schema(name = "注册手机")
    private String phone;

    @Schema(name = "用户密码(可以为空，支持只用验证码注册、登录)")
    private String password;

    @Schema(name = "注册IP")
    private Integer regIp;

    @Schema(name = "是否有效用户")
    private Boolean accountNonExpired;

    @Schema(name = "账号是否未过期")
    private Boolean credentialsNonExpired;

    @Schema(name = "是否未锁定")
    private Boolean accountNonLocked;

    @Schema(name = "用户状态：E-能登录，D-不能登录", allowableValues = {"E", "D"})
    private StatusEnum status;

    @Schema(name = "是否删除")
    private Boolean deleted;

    @Schema(name = "创建时间")
    private Date createTime;

    @Schema(name = "最后修改时间")
    private Date lastModifyTime;

    @Schema(name = "描述")
    private String remark;

}