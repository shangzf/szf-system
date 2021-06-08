package com.shangzf.front.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(description = "广告位")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionSpaceVO implements Serializable {
    private static final long serialVersionUID = 2686705665662845793L;

    @Schema(description = "广告位名称")
    private String name;

    @Schema(description = "广告位Key")
    private String spaceKey;

    @Schema(description = "广告位描述")
    private String remark;

    @Schema(description = "广告位对应的广告")
    private List<PromotionAdVO> adVOList;

}
