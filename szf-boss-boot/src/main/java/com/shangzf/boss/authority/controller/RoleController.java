package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.dto.param.RoleParam;
import com.shangzf.boss.authority.vo.RoleVO;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.dto.valid.Insert;
import com.shangzf.common.web.pojo.dto.valid.Update;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "RoleController", description = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleRemoteService roleRemoteService;

    @Operation(summary = "保存角色", description = "保存角色")
    @PostMapping("/")
    public ResultResponse<?> save(@Validated({Insert.class}) @RequestBody RoleDTO dto) {
        boolean result = roleRemoteService.save(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "更新角色", description = "更新角色")
    @PutMapping("/")
    public ResultResponse<?> update(@Validated({Update.class}) @RequestBody RoleDTO dto) {
        boolean result = roleRemoteService.update(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "删除角色", description = "根据角色ID删除角色", parameters = {@Parameter(name = "id", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @DeleteMapping("/")
    public ResultResponse<?> delete(@Validated @NotNull @RequestParam("id") Long id) {
        boolean result = roleRemoteService.delete(id);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "获取单个角色", description = "根据ID获取单个角色", parameters = {@Parameter(name = "id", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/")
    public ResultResponse<RoleVO> getById(@Validated @NotNull @RequestParam("id") Long id) {
        RoleDTO dto = roleRemoteService.getById(id);
        return ResultResponse.successOfData(ConvertUtil.convert(dto, RoleVO.class));
    }

    @GetMapping("/all")
    public ResultResponse<List<RoleVO>> getAll() {
        List<RoleDTO> list = roleRemoteService.getAll();
        return ResultResponse.successOfData(ConvertUtil.convertList(list, RoleVO.class));
    }

    @Operation(summary = "获取角色", description = "根据用户ID获取角色，结果为列表", parameters = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/roles")
    public ResultResponse<List<RoleVO>> getRolesByUserId(@Validated @NotNull @RequestParam("userId") Long userId) {
        List<RoleDTO> list = roleRemoteService.getRolesByUserId(userId);
        return ResultResponse.successOfData(ConvertUtil.convertList(list, RoleVO.class));
    }

    @Operation(summary = "给用户分配角色", description = "给用户分配角色是否成功")
    @PostMapping("/allocate")
    public ResultResponse<?> allocateUserRoles(@Validated @RequestBody AllocateUserRolesDTO dto) {
        boolean result = roleRemoteService.allocateUserRoles(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @Operation(summary = "角色分页数据", description = "根据条件获取角色分页数据")
    @GetMapping("/page")
    public ResultResponse<DataGrid<RoleVO>> getRolesByPage(RoleParam param) {
        DataGrid<RoleDTO> dataGrid = roleRemoteService.getRolesByPage(param);
        List<RoleVO> voList = ConvertUtil.convertList(dataGrid.getRecords(), RoleVO.class);
        return ResultResponse.successOfData(new DataGrid<>(voList, dataGrid));
    }
}
