package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(name = "资源")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceDTO extends AuthorityBaseDTO{
    private static final long serialVersionUID = 7064201629783810598L;

    /**
     * 资源名称
     */
    @Schema(name = "资源名称")
    private String name;

    /**
     * 资源地址
     */
    @Schema(name = "资源地址")
    private String url;

    /**
     * 资源类别
     */
    @Schema(name = "资源类别ID")
    private Long categoryId;

    /**
     * 资源描述
     */
    @Schema(name = "资源描述")
    private String remark;

    /**
     * 是否被选中
     */
    @Schema(name = "是否被选中")
    private Boolean selected;
}
