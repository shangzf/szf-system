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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Tag(name = "ResourceController", description = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IResourceCategoryService resourceCategoryService;

    @Operation(summary = "获取资源", description = "根据ID获取资源")
    @GetMapping("/resource/{id}")
    public ResourceDTO getResourceById(@PathVariable("id") Long id) {
        Resource resources = resourceService.getById(id);
        return ConvertUtil.convert(resources, ResourceDTO.class);
    }

    @Operation(summary = "保存资源", description = "保存资源")
    @PostMapping("/")
    public Boolean save(@RequestBody ResourceDTO dto) {
        Resource resource = ConvertUtil.convert(dto, Resource.class);
        if (Objects.isNull(resource)) {
            return Boolean.FALSE;
        }
        return resourceService.saveResource(resource);
    }

    @Operation(summary = "更新资源", description = "更新资源")
    @PutMapping("/")
    public Boolean update(@RequestBody ResourceDTO dto) {
        Resource resource = ConvertUtil.convert(dto, Resource.class);
        if (Objects.isNull(resource)) {
            return Boolean.FALSE;
        }
        return resourceService.updateResource(resource);
    }

    @Operation(summary = "删除资源分类", description = "根据资源ID删除资源分类", parameters = {@Parameter(name = "id", description = "资源ID", in = ParameterIn.QUERY, required = true)})
    @DeleteMapping("/category")
    public boolean deleteCategory(@RequestParam("id") Long id) {
        List<Resource> resourceList = resourceService.getByCategoryId(id);
        if (CollectionUtils.isNotEmpty(resourceList)) {
            return Boolean.FALSE;
        }
        return resourceCategoryService.delete(id);
    }

    @Operation(summary = "资源列表", description = "根据角色ID获取角色的资源列表")
    @GetMapping("/resources/{roleId}")
    public List<ResourceDTO> getResourcesByRoleId(@PathVariable("roleId") Long roleId) {
        List<Resource> resources = resourceService.getByRoleId(roleId);
        return ConvertUtil.convertList(resources, ResourceDTO.class);
    }

    @Operation(summary = "给角色分配资源", description = "给角色分配资源是否成功")
    @PostMapping("/allocate")
    public boolean allocateRoleResources(@RequestBody AllocateRoleResourceDTO dto) {
        return resourceService.allocateRoleResources(dto);
    }

}
