package com.shangzf.authority.service;

import com.shangzf.authority.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户-角色 服务类
 * </p>
 *
 * @author 
 * @since 2021-05-20
 */
public interface IUserRoleService extends IService<UserRole> {

    boolean removeByRoleId(Long roleId);
}
