package com.shangzf.common.web.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Schema(name = "基础DTO")
@Data
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = 129086566425315670L;

    @Schema(name = "ID")
    private Long id;

    @Schema(name = "创建时间", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(name = "最后修改时间", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime;

}
