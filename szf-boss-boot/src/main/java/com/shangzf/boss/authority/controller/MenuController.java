package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import com.shangzf.authority.api.remote.IMenuRemoteService;
import com.shangzf.boss.authority.vo.MenuInfoVO;
import com.shangzf.boss.authority.vo.MenuNodeVO;
import com.shangzf.boss.authority.vo.MenuVO;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Tag(name = "MenuController", description = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuRemoteService menuRemoteService;

    @Operation(summary = "删除菜单")
    @DeleteMapping("/")
    public ResultResponse<?> delete(@RequestParam("id") Long id) {
        boolean result = menuRemoteService.delete(id);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    /**
     * 获取编辑菜单页面信息
     */
    @Operation(summary = "获取编辑菜单页面信息")
    @GetMapping("/info")
    public ResultResponse<MenuInfoVO> getMenuInfo(@RequestParam("id") Long id) {
        List<MenuNodeDTO> nodeList = menuRemoteService.getMenuNodeList();
        MenuDTO menu = menuRemoteService.getById(id);
        if (Objects.nonNull(menu)) {
            nodeList.forEach(menuNode -> setSelectFlag(menu.getParentId(), menuNode));
        }
        MenuInfoVO menuInfoVO = MenuInfoVO.builder().menuInfo(ConvertUtil.convert(menu, MenuVO.class)).parentMenus(ConvertUtil.convertList(nodeList, MenuNodeVO.class)).build();
        return ResultResponse.successOfData(menuInfoVO);
    }

    /**
     * 获取角色用于的菜单列表
     */
    @Operation(summary = "获取角色用于的菜单列表")
    @GetMapping("/menus")
    public ResultResponse<List<MenuNodeDTO>> getMenusByRoleId(@RequestParam("roleId") Long roleId) {
        List<MenuDTO> menuDTOList = menuRemoteService.getMenusByRoleId(roleId);
        List<MenuNodeDTO> nodeList = menuRemoteService.getMenuNodeList();
        List<Long> roleMenus = menuDTOList.stream().map(MenuDTO::getId).distinct().collect(Collectors.toList());
        nodeList.forEach(menuNode -> setSelectFlag(roleMenus, menuNode));
        return ResultResponse.successOfData(nodeList);
    }

    @PostMapping("/allocate")
    public ResultResponse<?> allocateRoleMenus(@Validated @RequestBody AllocateRoleMenusDTO dto){
        boolean result = menuRemoteService.allocateRoleMenus(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    /**
     * 选中菜单的上一级菜单，设置为选中状态
     */
    private void setSelectFlag(List<Long> roleMenus, MenuNodeDTO dto) {
        if (roleMenus.contains(dto.getId())) {
            dto.setSelected(Boolean.TRUE);
        }
        if (CollectionUtils.isNotEmpty(dto.getSubMenuList())) {
            dto.getSubMenuList().forEach(dto1 -> setSelectFlag(roleMenus, dto1));
        }
    }

    /**
     * 选中菜单的上一级菜单，设置为选中状态
     */
    private void setSelectFlag(Long parentId, MenuNodeDTO dto) {
        if (Objects.equals(dto.getId(), parentId)) {
            dto.setSelected(Boolean.TRUE);
            return;
        }
        if (CollectionUtils.isNotEmpty(dto.getSubMenuList())) {
            dto.getSubMenuList().forEach(dto1 -> setSelectFlag(parentId, dto1));
        }
    }

}
