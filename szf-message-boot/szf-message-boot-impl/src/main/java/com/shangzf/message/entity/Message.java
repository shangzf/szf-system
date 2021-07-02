package com.shangzf.message.entity;

import com.shangzf.common.pojo.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 消息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message extends BasePO {

    private static final long serialVersionUID = 5262447754265320452L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 主题
     */
    private String theme;

    /**
     * 内容
     */
    private String content;

    /**
     * 参考地址
     */
    private String referenceUrl;

    /**
     * 是否已读，0-已读，1-未读
     */
    private Boolean unread;

    /**
     * 是否删除，0-未删除，1-已删除
     */
    private Boolean deleted;

    /**
     * 备注
     */
    private String remark;

}
