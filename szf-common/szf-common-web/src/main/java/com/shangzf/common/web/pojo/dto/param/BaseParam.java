package com.shangzf.common.web.pojo.dto.param;

import com.shangzf.common.constant.PageConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "基础分页查询参数")
public abstract class BaseParam implements Serializable {

    private static final long serialVersionUID = 7694213510727876128L;

    @Schema(description = "当前页")
    private Long current;

    @Schema(description = "每页显示条数，默认10")
    private Long size;

    public BaseParam() {
        this(PageConstant.DEFAULT_CURRENT);
    }

    public BaseParam(Long current) {
        this(current, PageConstant.DEFAULT_SIZE);
    }

    public BaseParam(Long current, Long size) {
        this.current = current;
        this.size = size;
    }
}
