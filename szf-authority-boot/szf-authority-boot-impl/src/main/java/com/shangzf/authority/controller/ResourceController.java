package com.shangzf.authority.controller;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import com.shangzf.authority.entity.Resource;
import com.shangzf.authority.service.IResourceCategoryService;
import com.shangzf.authority.service.IResourceService;
import com.shangzf.common.util.ConvertUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ResourceController", description = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IResourceCategoryService resourceCategoryService;

    @Operation(summary = "删除资源分类", description = "根据资源ID删除资源分类", parameters = {@Parameter(name = "id", description = "资源ID", in = ParameterIn.QUERY, required = true)})
    @DeleteMapping("/category")
    public boolean deleteCategory(@RequestParam("id") Long id) {
        List<Resource> resourceList = resourceService.getByCategoryId(id);
        if (CollectionUtils.isNotEmpty(resourceList)) {
            return Boolean.FALSE;
        }
        return resourceCategoryService.delete(id);
    }

    @Operation(summary = "资源列表", description = "根据角色ID获取角色的资源列表", parameters = {@Parameter(name = "roleId", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/resources")
    public List<ResourceDTO> getResourcesByRoleId(@RequestParam("roleId") Long roleId) {
        List<Resource> resources = resourceService.getByRoleId(roleId);
        return ConvertUtil.convertList(resources, ResourceDTO.class);
    }

    @Operation(summary = "给角色分配资源", description = "给角色分配资源是否成功")
    @PostMapping("/allocate")
    public boolean allocateRoleResources(@RequestBody AllocateRoleResourceDTO dto) {
        return resourceService.allocateRoleResources(dto);
    }

}
