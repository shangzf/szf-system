package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "角色")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AuthorityBaseDTO {

    private static final long serialVersionUID = -8337468657774558021L;

    @Schema(description = "编码")
    @NotBlank(message = "编码不能为空")
    @Size(max = 20,message = "名称不能超过【20】个字符")
    private String code;

    @Schema(description = "名称")
    @NotBlank(message = "名称")
    @Size(max = 20,message = "名称不能超过【20】个字符")
    private String name;

    @Schema(description = "描述")
    @Size(max = 100,message = "描述内容不能超过【100】个字符")
    private String remark;
}
