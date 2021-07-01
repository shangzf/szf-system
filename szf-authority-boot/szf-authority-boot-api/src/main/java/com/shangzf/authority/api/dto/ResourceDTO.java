package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(description = "资源")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceDTO extends AuthorityBaseDTO{
    private static final long serialVersionUID = 7064201629783810598L;

    @Schema(description = "资源名称")
    private String name;

    @Schema(description = "资源地址")
    private String url;

    @Schema(description = "资源类别ID")
    private Long categoryId;

    @Schema(description = "资源描述")
    private String remark;

    @Schema(description = "是否被选中")
    private Boolean selected;
}
