package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "菜单节点，包括子菜单列表")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuNodeDTO extends MenuDTO {
    private static final long serialVersionUID = 6611855178241632100L;

    @Schema(name = "是否被选中，用于编辑菜单时标记所选中上一级菜单")
    private Boolean selected;

    @Schema(name = "子菜单列表")
    private List<MenuNodeDTO> subMenuList;
}