package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
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

@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleRemoteService roleRemoteService;

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public ResultResponseData<Boolean> delete(@PathVariable("id") Long id) {
        boolean result = roleRemoteService.delete(id);
        return ResultResponseData.success(result);
    }

    @GetMapping("/{id}")
    public ResultResponseData<RoleDTO> getById(@PathVariable("id") Long id) {
        RoleDTO dto = roleRemoteService.getById(id);
        return ResultResponseData.success(dto);
    }

    @GetMapping("/all")
    public ResultResponseData<List<RoleDTO>> getAll() {
        List<RoleDTO> list = roleRemoteService.getAll();
        return ResultResponseData.success(list);
    }

    @GetMapping("/roles/{userId}")
    public ResultResponseData<List<RoleDTO>> getRolesByUserId(@PathVariable("userId") Long userId) {
        List<RoleDTO> list = roleRemoteService.getRolesByUserId(userId);
        return ResultResponseData.success(list);
    }

    @PostMapping("/allocate")
    public ResultResponseData<Boolean> allocateUserRoles(@Validated @RequestBody AllocateUserRolesDTO dto){
        boolean result = roleRemoteService.allocateUserRoles(dto);
        return ResultResponseData.success(result);
    }
}
