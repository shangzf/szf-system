package com.shangzf.user.entity;

import com.shangzf.common.pojo.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户信息
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BasePO {
    private static final long serialVersionUID = 6293590295987098393L;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像地址
     */
    private String portrait;

    /**
     * 注册手机
     */
    private String phone;

    /**
     * 用户密码(可以为空，支持只用验证码注册、登录)
     */
    private String secret;

    /**
     * 注册IP
     */
    private Integer regIp;

    /**
     * 是否有效用户
     */
    private Boolean accountNonExpired;

    /**
     * 账号是否未过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 是否未锁定
     */
    private Boolean accountNonLocked;

    /**
     * 用户状态：E-能登录，D-不能登录
     */
    private String sign;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 描述
     */
    private String remark;


}
