package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.UserRole;
import com.shangzf.authority.mapper.UserRoleMapper;
import com.shangzf.authority.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getRoleId, roleId);
        return this.remove(queryWrapper);
    }
}
