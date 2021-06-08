package com.shangzf.boss.ad.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shangzf.ad.api.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Schema(description = "广告")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionAdVO implements Serializable {
    private static final long serialVersionUID = 8360913820128169202L;

    @Schema(description = "广告ID")
    private Long id;

    @Schema(description = "广告名称")
    private String name;

    @Schema(description = "广告位ID")
    private Long spaceId;

    @Schema(description = "广告精确搜索关键词")
    private String keyword;

    @Schema(description = "静态广告的内容")
    private String htmlContent;

    @Schema(description = "广告文字")
    private String text;

    @Schema(description = "广告链接")
    private String link;

    @Schema(description = "广告开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Schema(description = "广告结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @Schema(description = "状态(D-下架;U-上架)", allowableValues = {"D", "U"})
    private StatusEnum status;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "展示图片地址")
    private String img;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime;

    @Schema(description = "描述")
    private String remark;
}
