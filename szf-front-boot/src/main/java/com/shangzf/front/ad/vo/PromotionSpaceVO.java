package com.shangzf.front.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(name = "广告位")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionSpaceVO implements Serializable {
    private static final long serialVersionUID = 2686705665662845793L;

    @Schema(name = "广告位名称")
    private String name;

    @Schema(name = "广告位Key")
    private String spaceKey;

    @Schema(name = "广告位描述")
    private String remark;

    @Schema(name = "广告位对应的广告")
    private List<PromotionAdVO> adVOList;

}
