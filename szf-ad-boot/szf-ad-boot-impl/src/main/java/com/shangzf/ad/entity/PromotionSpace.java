package com.shangzf.ad.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 广告位
 * </p>
 */
@Data
public class PromotionSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

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
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lasModifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 描述
     */
    private String remark;


}
