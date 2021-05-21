package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.entity.Resource;
import com.shangzf.authority.mapper.ResourceMapper;
import com.shangzf.authority.service.IResourceService;
import com.shangzf.authority.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
        List<Long> menuIds = roleResourceService.queryResourceIdByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(menuIds);
    }

    @Override
    public boolean allocateRoleResources(AllocateRoleResourceDTO dto) {
        return false;
    }
}
