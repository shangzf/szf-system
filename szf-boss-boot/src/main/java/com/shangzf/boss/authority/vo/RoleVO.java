package com.shangzf.boss.authority.vo;


import com.shangzf.common.web.pojo.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends BaseVO {

    private static final long serialVersionUID = -7529800923100867007L;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String remark;
}
