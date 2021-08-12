package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Schema(description = "给角色分配菜单")
@Data
@NoArgsConstructor
public class AllocateRoleMenusDTO implements Serializable {
    private static final long serialVersionUID = 484705641526230227L;

    @Schema(description = "角色ID")
    @NotNull(message = "角色不能为空")
    private Long roleId;

    @Schema(description = "菜单ID列表")
    @NotEmpty(message = "角色-菜单不能为空")
    private List<Long> roleMenus;

}
