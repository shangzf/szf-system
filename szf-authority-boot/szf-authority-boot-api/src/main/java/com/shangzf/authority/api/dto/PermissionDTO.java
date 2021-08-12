package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(description = "用户菜单、资源权限")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 7022017813149528352L;

    @Schema(description = "菜单列表")
    private List<MenuNodeDTO> menuList;

    @Schema(description = "资源列表")
    private List<ResourceDTO> resourceList;
}
