package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceCategoryNodeDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import com.shangzf.authority.api.remote.IResourceRemoteService;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    private IResourceRemoteService resourceRemoteService;

    @Operation(summary = "删除资源")
    @DeleteMapping("/")
    public ResultResponse<?> delete(@RequestParam("id") Long id) {
        boolean result = resourceRemoteService.delete(id);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "删除资源分类，如果资源分类下有资源，不允许删除")
    @DeleteMapping("/category")
    public ResultResponse<?> deleteCategory(@RequestParam("id") Long id) {
        boolean result = resourceRemoteService.deleteCategory(id);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "获取角色用于的资源列表")
    @GetMapping("/resources")
    public ResultResponse<List<ResourceCategoryNodeDTO>> getResourcesByRoleId(@RequestParam("roleId") Long roleId) {
        List<ResourceDTO> resourceList = resourceRemoteService.getResourcesByRoleId(roleId);
        return null;
    }

    @PostMapping("/allocate")
    public ResultResponse<?> allocateRoleResources(@Validated @RequestBody AllocateRoleResourceDTO dto) {
        boolean result = resourceRemoteService.allocateRoleResources(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

}
