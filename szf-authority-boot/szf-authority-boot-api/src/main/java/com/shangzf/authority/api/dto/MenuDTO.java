package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(name = "菜单")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends AuthorityBaseDTO {

    private static final long serialVersionUID = 6309715598037273458L;
    /**
     * 父菜单ID,顶级菜父菜单ID为0
     */
    @Schema(name = "父菜单ID,顶级菜父菜单ID为0")
    private Long parentId;

    /**
     * 菜单地址
     */
    @Schema(name = "菜单地址")
    private String href;

    /**
     * 菜单图标
     */
    @Schema(name = "菜单图标")
    private String icon;

    /**
     * 菜单名称
     */
    @Schema(name = "菜单名称")
    private String name;

    /**
     * 菜单描述
     */
    @Schema(name = "菜单描述")
    private String remark;

    /**
     * 是否显示
     */
    @Schema(name = "是否显示")
    private Boolean shown;

    /**
     * 排序序号
     */
    @Schema(name = "排序序号")
    private Integer orderNum;

    /**
     * 菜单层级，从0开始，越大层级越低
     */
    @Schema(name = "菜单层级，从0开始，越大层级越低")
    private Integer level;
}
