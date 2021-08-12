package com.shangzf.user.api.dto;

import com.shangzf.common.web.pojo.dto.BaseDTO;
import com.shangzf.user.api.dto.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "微信")
@EqualsAndHashCode(callSuper = true)
public class WeixinDTO extends BaseDTO {

    private static final long serialVersionUID = -6540227078113350749L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "认证ID，对应微信的unionID")
    private String unionId;

    @Schema(description = "openID")
    private String openId;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "头像")
    private String portrait;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "性别(M-男性,F-女性,U-未知)", allowableValues = {"M", "F", "U"})
    private SexEnum sex;

    @Schema(description = "是否删除")
    private Boolean deleted;

    @Schema(name = "描述")
    private String remark;
}
