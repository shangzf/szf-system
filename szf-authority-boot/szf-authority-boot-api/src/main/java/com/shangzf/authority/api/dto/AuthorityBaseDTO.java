package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class AuthorityBaseDTO implements Serializable {

    private static final long serialVersionUID = 5305252239737887218L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "最后修改时间")
    private Date lastModifyTime;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "最后修改者")
    private String lasModifyBy;
}
