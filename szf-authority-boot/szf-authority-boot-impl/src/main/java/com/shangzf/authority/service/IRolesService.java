package com.shangzf.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.param.RoleParam;
import com.shangzf.authority.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色
 *
 * </p>
 *
 * @author
 * @since 2021-05-20
 */
public interface IRolesService extends IService<Roles> {

    /**
     * 根据ID级联删除角色
     * 并关联删除用户-角色，角色-资源，角色-菜单
     */
    boolean deleteWithAssociation(Long id);

    List<Roles> getRolesByUserId(Long userId);

    List<Roles> getAll();

    /**
     * 给用户分配角色
     */
    boolean allocateUserRoles(AllocateUserRolesDTO dto);

    Roles getRolesById(Long id);

    Page<Roles> getRolesByPage(RoleParam param);

    Boolean saveRole(Roles roles);

    Boolean updateRole(Roles roles);
}
