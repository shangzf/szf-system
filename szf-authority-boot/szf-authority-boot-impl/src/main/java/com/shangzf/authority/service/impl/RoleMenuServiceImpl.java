package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.RoleMenu;
import com.shangzf.authority.mapper.RoleMenuMapper;
import com.shangzf.authority.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色-菜单 服务实现类
 * </p>
 */
@Slf4j
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        log.info("[removeByRoleId]参数:{}", roleId);
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, roleId);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByMenuId(Long menuId) {
        log.info("[removeByMenuId]参数:{}", menuId);
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getMenuId, menuId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<Long> queryMenuIdByRoleId(Long roleId) {
        log.info("[queryMenuIdByRoleId]参数:{}", roleId);
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, roleId);
        List<RoleMenu> roleMenus = this.list(queryWrapper);
        return roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByMenuIdByRoleIds(Long roleId, List<Long> menuIds) {
        log.info("[removeByMenuIdByRoleIds]参数:{},{}", roleId, JSON.toJSONString(menuIds));
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, roleId).in(RoleMenu::getMenuId, menuIds);
        return this.remove(queryWrapper);
    }

    @Override
    public List<Long> queryMenuIdByRoleIds(List<Long> roleIds) {
        log.info("[queryMenuIdByRoleIds]参数:{}", JSON.toJSONString(roleIds));
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(RoleMenu::getRoleId, roleIds);
        List<RoleMenu> roleMenus = this.list(queryWrapper);
        return roleMenus.stream().map(RoleMenu::getMenuId).distinct().collect(Collectors.toList());
    }
}
