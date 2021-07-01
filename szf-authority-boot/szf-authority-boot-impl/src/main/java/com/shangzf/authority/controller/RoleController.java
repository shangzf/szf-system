package com.shangzf.authority.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.dto.param.RoleParam;
import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Tag(name = "RoleController", description = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRolesService rolesService;

    @Operation(summary = "获取角色", description = "根据用户ID获取角色，结果为列表", parameters = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/roles")
    public List<RoleDTO> getRolesByUserId(@RequestParam("userId") Long userId) {
        List<Roles> roles = rolesService.getRolesByUserId(userId);
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @Operation(summary = "保存角色", description = "保存角色")
    @PostMapping("/")
    public Boolean save(@RequestBody RoleDTO dto) {
        Roles roles = ConvertUtil.convert(dto, Roles.class);
        if (Objects.isNull(roles)) {
            return Boolean.FALSE;
        }
        return rolesService.saveRole(roles);
    }

    @Operation(summary = "更新角色", description = "更新角色")
    @PutMapping("/")
    public Boolean update(@RequestBody RoleDTO dto) {
        Roles roles = ConvertUtil.convert(dto, Roles.class);
        if (Objects.isNull(roles)) {
            return Boolean.FALSE;
        }
        return rolesService.updateRole(roles);
    }

    @Operation(summary = "删除角色", description = "根据角色ID删除角色", parameters = {@Parameter(name = "id", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @DeleteMapping("/")
    public boolean delete(@RequestParam("id") Long id) {
        return rolesService.deleteWithAssociation(id);
    }

    @Operation(summary = "获取单个角色", description = "根据ID获取单个角色", parameters = {@Parameter(name = "id", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/")
    public RoleDTO getById(@RequestParam("id") Long id) {
        Roles roles = rolesService.getRolesById(id);
        return ConvertUtil.convert(roles, RoleDTO.class);
    }

    @Operation(summary = "获取全部角色", description = "获取全部角色，结果为列表")
    @GetMapping("/all")
    public List<RoleDTO> getAll() {
        List<Roles> roles = rolesService.getAll();
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @Operation(summary = "给用户分配角色", description = "给用户分配角色是否成功")
    @PostMapping("/allocate")
    public boolean allocateUserRoles(@RequestBody AllocateUserRolesDTO dto) {
        return rolesService.allocateUserRoles(dto);
    }

    @Operation(summary = "角色分页数据", description = "根据条件获取角色分页数据")
    @GetMapping("/page")
    public DataGrid<RoleDTO> getRolesByPage(RoleParam param) {
        Page<Roles> rolesPage = rolesService.getRolesByPage(param);
        List<RoleDTO> dtoList = ConvertUtil.convertList(rolesPage.getRecords(), RoleDTO.class);
        return new DataGrid<>(dtoList, rolesPage.getTotal(), rolesPage.getSize(), rolesPage.getCurrent());
    }
}
