package com.shangzf.boss.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Schema(description = "广告位")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionSpaceVO implements Serializable {

    private static final long serialVersionUID = 4645665692159124299L;

    @Schema(name = "广告位ID")
    private Long id;

    @Schema(name = "广告位名称")
    private String name;

    @Schema(name = "广告位Key")
    private String spaceKey;

    @Schema(name = "广告位创建时间")
    private Date createTime;

    @Schema(name = "广告位最后修改时间")
    private Date lastModifyTime;

    @Schema(name = "广告位是否删除")
    private Boolean deleted;

    @Schema(name = "广告位描述")
    private String remark;

    @Schema(name = "广告位对应的广告")
    private List<PromotionAdVO> adVOList;
}
