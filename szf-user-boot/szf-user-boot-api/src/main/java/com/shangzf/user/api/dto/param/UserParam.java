package com.shangzf.user.api.dto.param;

import com.shangzf.common.web.pojo.dto.param.BaseQueryParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户分页查询")
public class UserParam extends BaseQueryParam {

    private static final long serialVersionUID = -8504131990994568056L;

    @Schema(description = "手机号码")
    private String phone;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "创建时间")
    private List<String> createTime;
}
