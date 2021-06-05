package com.shangzf.common.web.pojo.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(name = "基本查询参数")
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseQueryParam extends BaseParam {
    private static final long serialVersionUID = -598315986294728276L;

    @Schema(name = "查询的值")
    private String query;
}
