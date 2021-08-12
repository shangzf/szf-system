package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(description = "菜单")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends AuthorityBaseDTO implements Comparable<MenuDTO> {

    private static final long serialVersionUID = 6309715598037273458L;

    @Schema(description = "父菜单ID,顶级菜父菜单ID为0")
    private Long parentId;

    @Schema(description = "菜单地址")
    private String href;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单描述")
    private String remark;

    @Schema(description = "是否显示")
    private Boolean shown;

    @Schema(description = "排序序号")
    private Integer orderNum;

    @Schema(description = "菜单层级，从0开始，越大层级越低")
    private Integer level;

    @Override
    public int compareTo(MenuDTO o) {
        return this.getOrderNum() - o.getOrderNum();
    }
}
