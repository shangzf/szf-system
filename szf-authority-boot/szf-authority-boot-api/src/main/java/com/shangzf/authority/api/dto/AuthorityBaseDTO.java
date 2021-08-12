package com.shangzf.authority.api.dto;

import com.shangzf.common.web.pojo.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorityBaseDTO extends BaseDTO {

    private static final long serialVersionUID = 5305252239737887218L;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "最后修改者")
    private String lasModifyBy;
}

