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
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
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
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    /**
     * 系统中所有资源url转化成的RequestMatcher集合，用于匹配请求中的url
     */
    //private static final Set<MvcRequestMatcher> RESOURCE_CONFIG_ATTRIBUTES = new HashSet<>();

    @Override
    public synchronized void loadResource() {
        List<Resource> resources = this.list();
        //resources.forEach(resource -> RESOURCE_CONFIG_ATTRIBUTES.add(this.newMvcRequestMatcher(resource.getUrl())));
        //log.debug("init resourceConfigAttributes:{}", RESOURCE_CONFIG_ATTRIBUTES);
    }

    @Autowired
    private IRoleResourceService roleResourceService;

    @Override
    public boolean matchRequestUrl(HttpServletRequest authRequest) {
        // 能找到匹配的url就返回true。不比对method域
        //return RESOURCE_CONFIG_ATTRIBUTES.stream().anyMatch(requestMatcher -> requestMatcher.matches(authRequest));
        return false;
    }

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
    public boolean matchUserResources(List<Long> roleIds, HttpServletRequest request) {
        boolean existInResources = this.matchRequestUrl(request);
        if (!existInResources) {
            log.info("url未在资源池中找到，拒绝访问: url:{}", request.getServletPath());
            return false;
        }
        List<Resource> resources = this.queryByRoleIds(roleIds);
        for (Resource resource : resources) {
            //NewMvcRequestMatcher matcher = this.newMvcRequestMatcher(resource.getUrl());
            //if (matcher.matches(request)) {
            //    return true;
            //}
        }
        return false;
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
            List<RoleResource> roleResourceList = needToInsertResources.stream().map(resourceId -> {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(dto.getRoleId());
                roleResource.setResourceId(resourceId);
                return roleResource;
            }).collect(Collectors.toList());
            resultIns = roleResourceService.saveBatch(roleResourceList);
        }
        return resultDel && resultIns;
    }

//    private NewMvcRequestMatcher newMvcRequestMatcher(String url) {
//        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, null);
//    }
}
