package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.boss.authority.vo.AllocateUserRolesVO;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.vo.response.ResultResponseData;
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

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleRemoteService roleRemoteService;

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

    @PostMapping("/allocateUserRoles")
    public ResultResponseData<Boolean> allocateUserRoles(@Validated @RequestBody AllocateUserRolesVO vo){
        boolean result = roleRemoteService.allocateUserRoles(ConvertUtil.convert(vo, AllocateUserRolesDTO.class));
        return ResultResponseData.success(result);
    }
}
