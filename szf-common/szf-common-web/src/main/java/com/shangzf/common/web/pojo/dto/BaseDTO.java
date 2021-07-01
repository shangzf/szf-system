package com.shangzf.common.web.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shangzf.common.web.pojo.dto.valid.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "基础DTO")
@EqualsAndHashCode(of = "id")
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = 129086566425315670L;

    @Schema(description = "ID")
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "创建时间", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "最后修改时间", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime;

}
