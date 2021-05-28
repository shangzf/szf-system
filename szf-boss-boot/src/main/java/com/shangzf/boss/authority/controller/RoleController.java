package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.common.vo.response.ResultResponse;
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

@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleRemoteService roleRemoteService;

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public ResultResponse<?> delete(@PathVariable("id") Long id) {
        boolean result = roleRemoteService.delete(id);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @GetMapping("/{id}")
    public ResultResponse<RoleDTO> getById(@PathVariable("id") Long id) {
        RoleDTO dto = roleRemoteService.getById(id);
        return ResultResponse.success(dto);
    }

    @GetMapping("/all")
    public ResultResponse<List<RoleDTO>> getAll() {
        List<RoleDTO> list = roleRemoteService.getAll();
        return ResultResponse.success(list);
    }

    @GetMapping("/roles/{userId}")
    public ResultResponse<List<RoleDTO>> getRolesByUserId(@PathVariable("userId") Long userId) {
        List<RoleDTO> list = roleRemoteService.getRolesByUserId(userId);
        return ResultResponse.success(list);
    }

    @PostMapping("/allocate")
    public ResultResponse<?> allocateUserRoles(@Validated @RequestBody AllocateUserRolesDTO dto){
        boolean result = roleRemoteService.allocateUserRoles(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }
}
