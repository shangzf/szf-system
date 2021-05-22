package com.shangzf.authority.service;

import com.shangzf.authority.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-菜单 服务类
 * </p>
 *
 * @author 
 * @since 2021-05-20
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    boolean removeByRoleId(Long roleId);

    boolean removeByMenuId(Long menuId);

    List<Long> queryMenuIdByRoleId(Long roleId);

    boolean removeByMenuIdByRoleIds(Long roleId, List<Long> menuIds);

    List<Long> queryMenuIdByRoleIds(List<Long> roleIds);
}
