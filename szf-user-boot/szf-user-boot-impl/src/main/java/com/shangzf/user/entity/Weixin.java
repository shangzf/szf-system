package com.shangzf.user.entity;

import com.shangzf.common.web.pojo.po.BasePO;
import com.shangzf.user.api.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 微信绑定信息
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Weixin extends BasePO {

    private static final long serialVersionUID = 1041313145592392408L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 认证ID，对应微信的unionID
     */
    private String unionId;

    /**
     * openID
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 描述
     */
    private String remark;


}
