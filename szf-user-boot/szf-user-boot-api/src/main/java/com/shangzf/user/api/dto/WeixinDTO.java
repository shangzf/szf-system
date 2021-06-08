package com.shangzf.user.api.dto;

import com.shangzf.user.api.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Schema(name = "微信")
@Data
public class WeixinDTO implements Serializable {

    private static final long serialVersionUID = -6540227078113350749L;

    @Schema(name = "ID")
    private Long id;

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "认证ID，对应微信的unionID")
    private String unionId;

    @Schema(name = "openID")
    private String openId;

    @Schema(name = "昵称")
    private String nickName;

    @Schema(name = "头像")
    private String portrait;

    @Schema(name = "城市")
    private String city;

    @Schema(name = "性别(M-男性,F-女性,U-未知)", allowableValues = {"M", "F", "U"})
    private SexEnum sex;

    @Schema(name = "是否删除")
    private Boolean deleted;

    @Schema(name = "创建时间")
    private Date createTime;

    @Schema(name = "最后修改时间")
    private Date lastModifyTime;

    @Schema(name = "描述")
    private String remark;
}
