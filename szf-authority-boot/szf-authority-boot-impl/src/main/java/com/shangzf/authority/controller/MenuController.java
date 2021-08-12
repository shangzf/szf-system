package com.shangzf.authority.controller;

import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import com.shangzf.authority.entity.Menu;
import com.shangzf.authority.service.IMenuService;
import com.shangzf.common.util.ConvertUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Operation(summary = "删除菜单", description = "根据菜单ID删除菜单", parameters = {@Parameter(name = "id", description = "菜单ID", in = ParameterIn.QUERY, required = true)})
    @DeleteMapping("/")
    public boolean delete(@RequestParam("id") Long id) {
        return menuService.deleteWithAssociation(id);
    }

    @Operation(summary = "返回菜单树", description = "返回菜单树")
    @GetMapping("/node")
    public List<MenuNodeDTO> getMenuNodeList() {
        return menuService.queryMenuNodeList();
    }

    @Operation(summary = "查询单个菜单", description = "根据菜单ID查询单个菜单", parameters = {@Parameter(name = "id", description = "菜单ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/")
    public MenuDTO getById(@RequestParam("id") Long id) {
        Menu menu = menuService.getById(id);
        return ConvertUtil.convert(menu, MenuDTO.class);
    }

    @Operation(summary = "菜单列表", description = "获取角色用于的菜单列表", parameters = {@Parameter(name = "roleId", description = "角色ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/menus")
    public List<MenuDTO> getMenusByRoleId(@RequestParam("roleId") Long roleId) {
        List<Menu> menus = menuService.getByRoleId(roleId);
        return ConvertUtil.convertList(menus, MenuDTO.class);
    }

    @Operation(summary = "给角色分配菜单", description = "给角色分配菜单是否成功")
    @PostMapping("/allocate")
    public boolean allocateRoleMenus(@RequestBody AllocateRoleMenusDTO dto) {
        return menuService.allocateRoleMenus(dto);
    }
}
