package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangzf.authority.entity.RoleResource;
import com.shangzf.authority.mapper.RoleResourceMapper;
import com.shangzf.authority.service.IRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色-资源 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-20
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        return this.remove(queryWrapper);
    }
}
