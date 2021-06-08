package com.shangzf.ad.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shangzf.ad.api.enums.StatusEnum;
import com.shangzf.common.web.pojo.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(description = "广告")
@Data
@EqualsAndHashCode(callSuper = true)
public class PromotionAdDTO extends BaseDTO {

    private static final long serialVersionUID = 8867429022120888656L;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "广告位ID")
    private Long spaceId;

    @Schema(description = "精确搜索关键词")
    private String keyword;

    @Schema(description = "静态广告的内容")
    private String htmlContent;

    @Schema(description = "文字")
    private String text;

    @Schema(description = "链接")
    private String link;

    @Schema(description = "开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Schema(description = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @Schema(description = "状态(U-上架/D-下架)", allowableValues = {"U", "D"})
    private StatusEnum status;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "展示图片地址")
    private String img;

    @Schema(description = "描述")
    private String remark;
}
