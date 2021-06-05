package com.shangzf.ad.api.dto;

import com.shangzf.common.web.pojo.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(name = "广告位")
@Data
@EqualsAndHashCode(callSuper = true)
public class PromotionSpaceDTO extends BaseDTO {

    private static final long serialVersionUID = -6866344964992782184L;

    @Schema(name = "广告位名称")
    private String name;

    @Schema(name = "广告位Key")
    private String spaceKey;

    @Schema(name = "广告位描述")
    private String remark;

    @Schema(name = "是否删除")
    private Boolean deleted;

    @Schema(name = "广告位对应的广告")
    private List<PromotionAdDTO> adDTOList;
}
