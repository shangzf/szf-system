package com.shangzf.ad.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 广告
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionAd implements Serializable {

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
     * 广告位ID
     */
    private byte[] spaceId;

    /**
     * 精确搜索关键词
     */
    private String keyword;

    /**
     * 静态广告的内容
     */
    private String htmlContent;

    /**
     * 文字
     */
    private String text;

    /**
     * 链接
     */
    private String link;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态(S/U/D)
     */
    private String status;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 展示图片地址
     */
    private String img;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime lasModifyTime;

    /**
     * 描述
     */
    private String remark;


}
