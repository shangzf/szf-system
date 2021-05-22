package com.shangzf.boss.authority.vo;

import com.shangzf.common.vo.request.RequestData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name = "分配用户-角色")
@Data
@NoArgsConstructor
public class AllocateUserRolesVO implements RequestData {
    private static final long serialVersionUID = 7284921427974573691L;

    @Schema(name = "用户ID")
    @NotNull(message = "用户不能为空")
    private Long userId;
    @Schema(name = "角色ID列表")
    @NotEmpty(message = "用户-角色不能为空")
    private List<Long> userRoles;
}
