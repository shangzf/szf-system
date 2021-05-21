package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.entity.Menu;
import com.shangzf.authority.entity.RoleMenu;
import com.shangzf.authority.mapper.MenuMapper;
import com.shangzf.authority.service.IMenuService;
import com.shangzf.authority.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<Menu> queryByParentId(Long id) {
        log.info("[queryByParentId]参数:{}", id);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, id);
        return this.list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteWithAssociation(Long id) {
        log.info("[deleteWithAssociation]参数:{}", id);
        deleteMenuCascade(id);
        return Boolean.TRUE;
    }

    @Override
    public List<Menu> queryByLevel(Integer level) {
        log.info("[queryByLevel]参数:{}", level);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getLevel, level);
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> getByRoleId(Long roleId) {
        log.info("[getByRoleId]参数:{}", roleId);
        List<Long> menuIds = roleMenuService.queryMenuIdByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(menuIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean allocateRoleMenus(AllocateRoleMenusDTO dto) {
        log.info("[allocateRoleMenus]参数:{}", JSON.toJSONString(dto));
        // 角色已拥有的菜单
        List<Long> menuIds = roleMenuService.queryMenuIdByRoleId(dto.getRoleId());

        // 需要删除的菜单ID
        List<Long> needToDeleteMenus = menuIds.stream().filter(menuId -> !dto.getRoleMenus().contains(menuId))
                                              .distinct().collect(Collectors.toList());
        // 需要插入的菜单ID
        List<Long> needToInsertMenus = dto.getRoleMenus().stream().filter(menuId -> !menuIds.contains(menuId))
                                          .distinct().collect(Collectors.toList());
        boolean resultDel = true;
        boolean resultIns = true;
        if (CollectionUtils.isNotEmpty(needToDeleteMenus)) {
            resultDel = roleMenuService.removeByMenuIdByRoleIds(dto.getRoleId(), needToDeleteMenus);
        }
        if (CollectionUtils.isNotEmpty(needToInsertMenus)) {
            List<RoleMenu> roleMenuList = needToInsertMenus.stream()
                                                           .map(menuId -> new RoleMenu(dto.getRoleId(), menuId))
                                                           .collect(Collectors.toList());
            resultIns = roleMenuService.saveBatch(roleMenuList);
        }
        return resultDel && resultIns;
    }

    /**
     * 级联删除所有子菜单，及子菜单绑定的角色关系
     */
    private void deleteMenuCascade(Long id) {
        List<Menu> menus = this.queryByParentId(id);
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> deleteMenuCascade(menu.getId()));
        }
        roleMenuService.removeByMenuId(id);
        this.removeById(id);
    }
}
