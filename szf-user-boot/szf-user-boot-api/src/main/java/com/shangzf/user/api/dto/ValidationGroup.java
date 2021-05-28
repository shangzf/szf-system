package com.shangzf.user.api.dto;

import javax.validation.GroupSequence;

/**
 * 验证组
 */
public interface ValidationGroup {

    /**
     * 验证用户ID
     */
    interface ValidationUserID {
    }

    /**
     * 验证密码
     */
    interface ValidationPassword {
    }

    /**
     * 验证确认密码
     */
    interface ValidationConfigPassword {
    }

    /**
     * 验证原始密码
     */
    interface ValidationSourcePassword {
    }

    /**
     * 保存密码时验证顺序
     */
    @GroupSequence({ValidationUserID.class, ValidationPassword.class, ValidationConfigPassword.class})
    interface SetPasswordValidationSequence {

    }

    /**
     * 修改密码时验证顺序
     */
    @GroupSequence({ValidationUserID.class, ValidationPassword.class, ValidationConfigPassword.class, ValidationSourcePassword.class})
    interface ChangePasswordValidationSequence {

    }
}
