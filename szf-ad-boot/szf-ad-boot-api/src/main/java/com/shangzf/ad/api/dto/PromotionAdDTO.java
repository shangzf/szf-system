package com.shangzf.ad.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shangzf.ad.api.enums.StatusEnum;
import com.shangzf.common.web.pojo.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(name = "广告")
@Data
@EqualsAndHashCode(callSuper = true)
public class PromotionAdDTO extends BaseDTO {

    private static final long serialVersionUID = 8867429022120888656L;

    @Schema(name = "名称")
    private String name;

    @Schema(name = "广告位ID")
    private Long spaceId;

    @Schema(name = "精确搜索关键词")
    private String keyword;

    @Schema(name = "静态广告的内容")
    private String htmlContent;

    @Schema(name = "文字")
    private String text;

    @Schema(name = "链接")
    private String link;

    @Schema(name = "开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Schema(name = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @Schema(name = "状态(U-上架/D-下架)", allowableValues = {"U", "D"})
    private StatusEnum status;

    @Schema(name = "优先级")
    private Integer priority;

    @Schema(name = "展示图片地址")
    private String img;

    @Schema(name = "描述")
    private String remark;
}
