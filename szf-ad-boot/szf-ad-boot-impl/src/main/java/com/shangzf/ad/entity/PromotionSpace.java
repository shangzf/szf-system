package com.shangzf.ad.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 广告位
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private byte[] id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位Key
     */
    private String spaceKey;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime lasModifyTime;

    /**
     * 是否删除(Y/N)
     */
    private String deleted;

    /**
     * 描述
     */
    private String remark;


}
