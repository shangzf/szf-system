package com.shangzf.authority.service;

import com.shangzf.authority.entity.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-资源 服务类
 * </p>
 *
 * @author 
 * @since 2021-05-20
 */
public interface IRoleResourceService extends IService<RoleResource> {

    boolean removeByRoleId(Long roleId);

    boolean removeByResourceId(Long resourceId);

    List<Long> queryResourceIdByRoleId(Long roleId);

    boolean removeByResourceIdByRoleIds(Long roleId, List<Long> resourceIds);

    List<Long> queryResourceIdByRoleIds(List<Long> roleIds);
}
