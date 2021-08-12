package com.shangzf.message.api.dto.param;

import com.shangzf.common.web.pojo.dto.param.BaseQueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息分页查询")
public class MessageParam extends BaseQueryParam {
    private static final long serialVersionUID = 5534541287449846936L;

}
