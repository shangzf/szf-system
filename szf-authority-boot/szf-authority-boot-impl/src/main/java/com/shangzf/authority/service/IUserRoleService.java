package com.shangzf.authority.service;

import com.shangzf.authority.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户-角色 服务类
 * </p>
 */
public interface IUserRoleService extends IService<UserRole> {

    boolean removeByRoleId(Long roleId);

    List<Long> queryRoleIdByUserId(Long userId);
}
