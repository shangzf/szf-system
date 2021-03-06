package com.shangzf.common.web.pojo.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "基本查询参数")
@EqualsAndHashCode(callSuper = true)
public abstract class BaseQueryParam extends BaseParam {
    private static final long serialVersionUID = -598315986294728276L;

    @Schema(description = "查询的值")
    private String query;
}
