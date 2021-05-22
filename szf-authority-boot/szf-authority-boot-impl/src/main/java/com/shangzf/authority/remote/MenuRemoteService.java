package com.shangzf.authority.remote;

import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import com.shangzf.authority.entity.Menu;
import com.shangzf.authority.service.IMenuService;
import com.shangzf.common.util.ConvertUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuRemoteService {

    @Autowired
    private IMenuService menuService;

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return menuService.deleteWithAssociation(id);
    }

    @Operation(summary = "返回菜单树")
    @GetMapping("/node")
    public List<MenuNodeDTO> getMenuNodeList() {
       return menuService.queryMenuNodeList();
    }

    @Operation(summary = "根据ID查询菜单")
    @GetMapping("/{id}")
    public MenuDTO getById(@PathVariable("id") Long id) {
        Menu menu = menuService.getById(id);
        return ConvertUtil.convert(menu, MenuDTO.class);
    }

    @Operation(summary = "获取角色用于的菜单列表")
    @GetMapping("/menus/{roleId}")
    public List<MenuDTO> getMenusByRoleId(@PathVariable("roleId") Long roleId){
        List<Menu> menus = menuService.getByRoleId(roleId);
        return ConvertUtil.convertList(menus, MenuDTO.class);
    }

    @PostMapping("/allocate")
    public boolean allocateRoleMenus(@RequestBody AllocateRoleMenusDTO dto) {
        return menuService.allocateRoleMenus(dto);
    }
}
