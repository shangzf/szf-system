package com.shangzf.ad.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PromotionSpaceDTO implements Serializable {

    private static final long serialVersionUID = -6866344964992782184L;
    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位Key
     */
    private String spaceKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lasModifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 描述
     */
    private String remark;

    private List<PromotionAdDTO> adDTOList;
}