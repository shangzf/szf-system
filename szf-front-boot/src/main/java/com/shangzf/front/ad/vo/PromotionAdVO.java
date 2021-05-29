package com.shangzf.front.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(name = "广告")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionAdVO implements Serializable {

    private static final long serialVersionUID = 619753900702024024L;

    @Schema(name = "广告名称")
    private String name;

    @Schema(name = "广告精确搜索关键词")
    private String keyword;

    @Schema(name = "静态广告的内容")
    private String htmlContent;

    @Schema(name = "广告文字")
    private String text;

    @Schema(name = "广告链接")
    private String link;

    @Schema(name = "优先级")
    private Integer priority;

    @Schema(name = "展示图片地址")
    private String img;

    @Schema(name = "描述")
    private String remark;
}
