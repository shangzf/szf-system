package com.shangzf.authority.api.dto.param;

import com.shangzf.common.web.pojo.dto.param.BaseQueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色分页查询")
public class RoleParam extends BaseQueryParam {
    private static final long serialVersionUID = -8261633482145726340L;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;
}
