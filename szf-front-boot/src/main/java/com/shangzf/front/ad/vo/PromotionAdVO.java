package com.shangzf.front.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(description = "广告")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionAdVO implements Serializable {

    private static final long serialVersionUID = 619753900702024024L;

    @Schema(description = "广告名称")
    private String name;

    @Schema(description = "广告精确搜索关键词")
    private String keyword;

    @Schema(description = "静态广告的内容")
    private String htmlContent;

    @Schema(description = "广告文字")
    private String text;

    @Schema(description = "广告链接")
    private String link;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "展示图片地址")
    private String img;

    @Schema(description = "描述")
    private String remark;
}
