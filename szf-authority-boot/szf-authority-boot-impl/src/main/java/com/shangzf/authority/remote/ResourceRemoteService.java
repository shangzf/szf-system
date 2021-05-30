package com.shangzf.authority.remote;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import com.shangzf.authority.entity.Resource;
import com.shangzf.authority.service.IResourceCategoryService;
import com.shangzf.authority.service.IResourceService;
import com.shangzf.common.util.ConvertUtil;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceRemoteService {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IResourceCategoryService resourceCategoryService;

    @Operation(summary = "删除资源分类")
    @DeleteMapping("/category")
    public boolean deleteCategory(@RequestParam("id") Long id) {
        List<Resource> resourceList = resourceService.getByCategoryId(id);
        if (CollectionUtils.isNotEmpty(resourceList)) {
            return Boolean.FALSE;
        }
        return resourceCategoryService.delete(id);
    }

    @Operation(summary = "获取角色用于的资源列表")
    @GetMapping("/resources")
    public List<ResourceDTO> getResourcesByRoleId(@RequestParam("roleId") Long roleId){
        List<Resource> resources = resourceService.getByRoleId(roleId);
        return ConvertUtil.convertList(resources, ResourceDTO.class);
    }

    @PostMapping("/allocate")
    public boolean allocateRoleResources(@RequestBody AllocateRoleResourceDTO dto) {
        return resourceService.allocateRoleResources(dto);
    }

}
