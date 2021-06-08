package com.shangzf.message.api.dto;

import com.shangzf.common.web.pojo.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息")
public class MessageDTO extends BaseDTO {
    private static final long serialVersionUID = -7607397660452307750L;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "参考地址")
    private String referenceUrl;

    @Schema(description = "是否已读，0-已读，1-未读")
    private Boolean unread;

    @Schema(description = "是否删除，0-未删除，1-已删除")
    private Boolean deleted;

    @Schema(description = "备注")
    private String remark;
}
