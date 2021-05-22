package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.RoleResource;
import com.shangzf.authority.mapper.RoleResourceMapper;
import com.shangzf.authority.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色-资源 服务实现类
 * </p>
 */
@Slf4j
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        log.info("[removeByRoleId]参数:{}", roleId);
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByResourceId(Long resourceId) {
        log.info("[removeByResourceId]参数:{}", resourceId);
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<Long> queryResourceIdByRoleId(Long roleId) {
        log.info("[queryResourceIdByRoleId]参数:{}", roleId);
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        List<RoleResource> roleResources = this.list(queryWrapper);
        return roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByResourceIdByRoleIds(Long roleId, List<Long> resourceIds) {
        log.info("[removeByResourceIdByRoleIds]参数:{},{}", roleId, JSON.toJSONString(resourceIds));
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId).in(RoleResource::getResourceId, resourceIds);
        return this.remove(queryWrapper);
    }

    @Override
    public List<Long> queryResourceIdByRoleIds(List<Long> roleIds) {
        log.info("[queryResourceIdByRoleIds]参数:{}", JSON.toJSONString(roleIds));
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(RoleResource::getRoleId, roleIds);
        List<RoleResource> roleResources = this.list(queryWrapper);
        return roleResources.stream().map(RoleResource::getResourceId).distinct().collect(Collectors.toList());
    }
}
