package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.param.RoleParam;
import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.entity.UserRole;
import com.shangzf.authority.mapper.RolesMapper;
import com.shangzf.authority.service.IRoleMenuService;
import com.shangzf.authority.service.IRoleResourceService;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.authority.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 */
@Slf4j
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IRoleResourceService roleResourceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteWithAssociation(Long id) {
        log.info("[deleteWithAssociation]参数:{}", id);
        userRoleService.removeByRoleId(id);
        roleMenuService.removeByRoleId(id);
        roleResourceService.removeByRoleId(id);
        return this.removeById(id);
    }

    @Override
    public List<Roles> getRolesByUserId(Long userId) {
        log.info("[getRolesByUserId]参数:{}", userId);
        List<Long> roleIds = userRoleService.queryRoleIdByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(roleIds);
    }

    @Override
    public List<Roles> getAll() {
        log.info("[getAll]");
        return this.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean allocateUserRoles(AllocateUserRolesDTO dto) {
        log.info("[allocateUserRoles]参数:{}", JSON.toJSONString(dto));
        // 获取当前用户的角色ID
        List<Long> roleIds = userRoleService.queryRoleIdByUserId(dto.getUserId());
        // 需要删除的角色ID
        List<Long> needToDeleteRoles = roleIds
                .stream()
                .filter(roleId -> !dto.getUserRoles().contains(roleId))
                .distinct()
                .collect(Collectors.toList());
        // 需要插入的角色ID
        List<Long> needToInsertRoles = dto.getUserRoles()
                                          .stream()
                                          .filter(roleId -> !roleIds.contains(roleId))
                                          .distinct()
                                          .collect(Collectors.toList());
        boolean resultDel = true;
        boolean resultIns = true;
        if (CollectionUtils.isNotEmpty(needToDeleteRoles)) {
            resultDel = userRoleService.removeByUserIdAndRoleIds(dto.getUserId(), needToDeleteRoles);
        }
        if (CollectionUtils.isNotEmpty(needToInsertRoles)) {
            List<UserRole> userRoleList = needToInsertRoles.stream().map(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(dto.getUserId());
                return userRole;
            }).collect(Collectors.toList());
            resultIns = userRoleService.saveBatch(userRoleList);
        }
        return resultDel && resultIns;
    }

    @Override
    public Roles getRolesById(Long id) {
        log.info("[getRolesById]参数:{}", id);
        return this.getById(id);
    }

    @Override
    public Page<Roles> getRolesByPage(RoleParam param) {
        log.info("[getRolesByPage]参数: {}", JSON.toJSONString(param));
        QueryWrapper<Roles> wrapper = new QueryWrapper<>();
        wrapper.lambda()
               .nested(StringUtils.isNotBlank(param.getQuery()), w -> w.like(Roles::getName, param.getQuery()).or()
                                                                       .like(Roles::getCode, param.getQuery()))
               .or(StringUtils.isNotBlank(param.getQuery()) || StringUtils.isNotBlank(param.getCode()), w -> w
                       .like(StringUtils.isNotBlank(param.getName()), Roles::getName, param.getName()).or()
                       .like(StringUtils.isNotBlank(param.getCode()), Roles::getCode, param.getCode()))
               .orderByDesc(Roles::getCreateTime);
        return this.page(new Page<>(param.getCurrent(), param.getSize()), wrapper);
    }

    @Override
    public Boolean saveRole(Roles roles) {
        log.info("[saveRole]参数: {}", JSON.toJSONString(roles));
        return this.save(roles);
    }

    @Override
    public Boolean updateRole(Roles roles) {
        log.info("[updateRole]参数: {}", JSON.toJSONString(roles));
        return this.updateById(roles);
    }
}
