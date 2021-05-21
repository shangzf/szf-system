package com.shangzf.boss.authority.vo;

import com.shangzf.common.vo.request.RequestData;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AllocateUserRolesVO implements RequestData {
    private static final long serialVersionUID = 7284921427974573691L;

    @NotNull(message = "用户不能为空")
    private Long userId;
    @NotEmpty(message = "用户-角色不能为空")
    private List<Long> userRoles;
}
