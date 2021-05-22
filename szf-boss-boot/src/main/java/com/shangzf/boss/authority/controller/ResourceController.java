package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceCategoryNodeDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import com.shangzf.authority.api.remote.IResourceRemoteService;
import com.shangzf.boss.authority.vo.AllocateRoleResourcesVO;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.vo.response.ResultResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceRemoteService resourceRemoteService;

    @Operation(summary = "删除资源")
    @DeleteMapping("/{id}")
    public ResultResponseData<Boolean> delete(@PathVariable("id") Long id) {
        boolean result = resourceRemoteService.delete(id);
        return ResultResponseData.success(result);
    }

    @Operation(summary = "删除资源分类，如果资源分类下有资源，不允许删除")
    @DeleteMapping("/category/{id}")
    public ResultResponseData<Boolean> deleteCategory(@PathVariable("id") Long id) {
        boolean result = resourceRemoteService.deleteCategory(id);
        return ResultResponseData.success(result);
    }

    @Operation(summary = "获取角色用于的资源列表")
    @GetMapping("/resources/{roleId}")
    public ResultResponseData<List<ResourceCategoryNodeDTO>> getResourcesByRoleId(@PathVariable("roleId") Long roleId){
        List<ResourceDTO> resourceList = resourceRemoteService.getResourcesByRoleId(roleId);
        return null;
    }

    @PostMapping("/allocate")
    public ResultResponseData<Boolean> allocateRoleResources(@Validated @RequestBody AllocateRoleResourcesVO vo){
        boolean result = resourceRemoteService.allocateRoleResources(ConvertUtil.convert(vo, AllocateRoleResourceDTO.class));
        return ResultResponseData.success(result);
    }

}
