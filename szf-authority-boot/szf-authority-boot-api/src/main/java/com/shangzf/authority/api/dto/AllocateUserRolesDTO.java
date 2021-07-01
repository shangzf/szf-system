package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Schema(description = "给用户分配角色")
@Data
@NoArgsConstructor
public class AllocateUserRolesDTO implements Serializable {

    private static final long serialVersionUID = -8084528415227985584L;

    @Schema(description = "用户ID")
    @NotNull(message = "用户不能为空")
    private Long userId;

    @Schema(description = "角色ID列表")
    @NotEmpty(message = "用户-角色不能为空")
    private List<Long> userRoles;

}
