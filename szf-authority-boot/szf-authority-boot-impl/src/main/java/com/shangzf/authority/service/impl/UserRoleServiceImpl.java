package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.UserRole;
import com.shangzf.authority.mapper.UserRoleMapper;
import com.shangzf.authority.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        log.info("[removeByRoleId]参数:{}", roleId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getRoleId, roleId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<Long> queryRoleIdByUserId(Long userId) {
        log.info("[removeByRoleId]参数:{}", userId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = this.list(queryWrapper);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByUserIdAndRoleIds(Long userId, List<Long> roleIds) {
        log.info("[removeByRoleId]参数:{},{}", userId, JSON.toJSONString(roleIds));
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userId).in(UserRole::getRoleId, roleIds);
        return this.remove(queryWrapper);
    }

}
