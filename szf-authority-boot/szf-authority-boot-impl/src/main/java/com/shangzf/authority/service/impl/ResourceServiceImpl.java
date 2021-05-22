package com.shangzf.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.entity.Resource;
import com.shangzf.authority.entity.RoleResource;
import com.shangzf.authority.mapper.ResourceMapper;
import com.shangzf.authority.service.IResourceService;
import com.shangzf.authority.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 */
@Slf4j
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    private IRoleResourceService roleResourceService;

    @Override
    public List<Resource> getByCategoryId(Long categoryId) {
        log.info("[getByCategoryId]参数:{}", categoryId);
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Resource::getCategoryId, categoryId);
        return this.list(queryWrapper);
    }

    @Override
    public List<Resource> getByRoleId(Long roleId) {
        log.info("[getByRoleId]参数:{}", roleId);
        List<Long> resourceIds = roleResourceService.queryResourceIdByRoleId(roleId);
        if (CollectionUtils.isEmpty(resourceIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(resourceIds);
    }

    @Override
    public List<Resource> queryByRoleIds(List<Long> roleIds) {
        log.info("[queryByRoleIds]参数:{}", JSON.toJSONString(roleIds));
        List<Long> resourceIds = roleResourceService.queryResourceIdByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(resourceIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(resourceIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean allocateRoleResources(AllocateRoleResourceDTO dto) {
        log.info("[allocateRoleResources]参数:{}", JSON.toJSONString(dto));
        // 角色已拥有的资源
        List<Long> resourceIds = roleResourceService.queryResourceIdByRoleId(dto.getRoleId());
        // 需要删除的资源ID
        List<Long> needToDeleteResources = resourceIds.stream().filter(resourceId -> !dto.getRoleResources()
                                                                                         .contains(resourceId))
                                                      .distinct().collect(Collectors.toList());
        // 需要插入的资源ID
        List<Long> needToInsertResources = dto.getRoleResources().stream()
                                              .filter(resourceId -> !resourceIds.contains(resourceId)).distinct()
                                              .collect(Collectors.toList());
        boolean resultDel = true;
        boolean resultIns = true;
        if (CollectionUtils.isNotEmpty(needToDeleteResources)) {
            resultDel = roleResourceService.removeByResourceIdByRoleIds(dto.getRoleId(), needToDeleteResources);
        }
        if (CollectionUtils.isNotEmpty(needToInsertResources)) {
            List<RoleResource> roleResourceList = needToInsertResources.stream()
                                                                       .map(resourceId -> RoleResource.builder()
                                                                                                      .roleId(dto
                                                                                                              .getRoleId())
                                                                                                      .resourceId(resourceId)
                                                                                                      .build())
                                                                       .collect(Collectors.toList());
            resultIns = roleResourceService.saveBatch(roleResourceList);
        }
        return resultDel && resultIns;
    }
}
