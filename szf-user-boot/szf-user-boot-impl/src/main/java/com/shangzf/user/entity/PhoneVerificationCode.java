package com.shangzf.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 验证码
 * </p>
 */
@Data
@TableName("user_phone_verification_code")
public class PhoneVerificationCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 验证码是否校验过
     */
    private Boolean checked;

    /**
     * 校验次数
     */
    private Integer checkTimes;


}
