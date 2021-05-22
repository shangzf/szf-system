package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "资源分类节点信息，按分类展示")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceCategoryNodeDTO extends ResourceCategoryDTO {
    private static final long serialVersionUID = -8732979196507006606L;

    @Schema(name = "资源列表")
    private List<ResourceDTO> resourceList;
}
