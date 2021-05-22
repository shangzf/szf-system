package com.shangzf.boss.authority.vo;

import com.shangzf.common.vo.request.RequestData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name = "分配角色-资源")
@Data
@NoArgsConstructor
public class AllocateRoleResourcesVO implements RequestData {
    private static final long serialVersionUID = 7284921427974573691L;

    @Schema(name = "角色ID")
    @NotNull(message = "角色不能为空")
    private Long roleId;
    @Schema(name = "资源ID列表")
    @NotEmpty(message = "角色-资源不能为空")
    private List<Long> roleResources;
}