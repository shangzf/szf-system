package com.shangzf.ad.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 广告
 * </p>

 */
@Data
public class PromotionAd implements Serializable {

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
     * 广告位ID
     */
    private Long spaceId;

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
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

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
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lasModifyTime;

    /**
     * 描述
     */
    private String remark;


}
