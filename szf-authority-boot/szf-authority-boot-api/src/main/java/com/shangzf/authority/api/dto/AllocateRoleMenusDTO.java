package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(name = "给角色分配菜单")
@Data
@NoArgsConstructor
public class AllocateRoleMenusDTO implements Serializable {
    private static final long serialVersionUID = 484705641526230227L;

    private Long roleId;
    private List<Long> roleMenus;

}
