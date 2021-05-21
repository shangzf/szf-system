package com.shangzf.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据父id查询菜单
     */
    List<Menu> queryByParentId(Long id);

    /**
     * 删除菜单，同时删除角色-菜单关联关系
     */
    boolean deleteWithAssociation(Long id);

    /**
     * 查询所以一级菜单
     */
    List<Menu> queryByLevel(Integer level);

    List<Menu> getByRoleId(Long roleId);

    /**
     * 给角色分配菜单
     */
    boolean allocateRoleMenus(AllocateRoleMenusDTO dto);
}
