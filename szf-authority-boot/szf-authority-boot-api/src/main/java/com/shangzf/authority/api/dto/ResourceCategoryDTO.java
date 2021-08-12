package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema(description = "资源类别")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceCategoryDTO extends AuthorityBaseDTO implements Comparable<ResourceCategoryDTO> {

    private static final long serialVersionUID = 5234888486767824131L;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别排序")
    private Integer sort;

    @Schema(description = "是否被选中")
    private Boolean selected;

    @Override
    public int compareTo(ResourceCategoryDTO o) {
        return this.getSort() - o.getSort();
    }
}
