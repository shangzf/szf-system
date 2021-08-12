package com.shangzf.boss.authority.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "菜单节点，包括子菜单列表")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuNodeVO extends MenuVO {
    private static final long serialVersionUID = 1443888975943842842L;

    @Schema(description = "是否被选中，用于编辑菜单时标记所选中上一级菜单")
    private Boolean selected;

    @Schema(description = "子菜单列表")
    private List<MenuNodeVO> subMenuList;
}
