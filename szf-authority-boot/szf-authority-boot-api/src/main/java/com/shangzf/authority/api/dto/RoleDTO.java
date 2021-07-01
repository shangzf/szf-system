package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(description = "角色")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AuthorityBaseDTO {

    private static final long serialVersionUID = -8337468657774558021L;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String remark;
}
