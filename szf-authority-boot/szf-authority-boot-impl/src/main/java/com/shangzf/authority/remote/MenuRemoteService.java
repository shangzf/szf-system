package com.shangzf.authority.remote;

import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import com.shangzf.authority.constant.MenuConstant;
import com.shangzf.authority.entity.Menu;
import com.shangzf.authority.service.IMenuService;
import com.shangzf.common.util.ConvertUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        // 查询一级菜单
        List<Menu> menus = menuService.queryByLevel(MenuConstant.TOP_LEVEL);
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        return menus.stream().map(this::fillMenuNode).collect(Collectors.toList());
    }

    /**
     * 填充菜单级别关系，将当前菜单的子菜单挂到当前菜单的子菜单列表，
     * 使用递归的方式逐级填充，直到菜单没有下一级菜单
     */
    private MenuNodeDTO fillMenuNode(Menu menu) {
        MenuNodeDTO menuNode = ConvertUtil.convert(menu, MenuNodeDTO.class);
        if (Objects.isNull(menuNode)) {
            return menuNode;
        }
        // 查询子菜单
        List<Menu> subMenus = menuService.queryByParentId(menuNode.getId());
        if (CollectionUtils.isEmpty(subMenus)) {
            return menuNode;
        }
        List<MenuNodeDTO> subMenuList = subMenus.stream().map(this::fillMenuNode).collect(Collectors.toList());
        menuNode.setSubMenuList(subMenuList);
        return menuNode;
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
