package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Schema(name = "给角色分配资源")
@Data
@NoArgsConstructor
public class AllocateRoleResourceDTO implements Serializable {

    private static final long serialVersionUID = 5390453807681931626L;
    @Schema(name = "角色ID")
    @NotNull(message = "角色不能为空")
    private Long roleId;
    @Schema(name = "资源ID列表")
    @NotEmpty(message = "角色-资源不能为空")
    private List<Long> roleResources;
}
