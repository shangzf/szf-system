package com.shangzf.authority.service.impl;

import com.shangzf.authority.api.dto.MenuNodeDTO;
import com.shangzf.authority.api.dto.PermissionDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import com.shangzf.authority.constant.MenuConstant;
import com.shangzf.authority.entity.Menu;
import com.shangzf.authority.entity.Resource;
import com.shangzf.authority.service.IAuthenticationService;
import com.shangzf.authority.service.IMenuService;
import com.shangzf.authority.service.IResourceService;
import com.shangzf.authority.service.IUserRoleService;
import com.shangzf.common.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean authenticate(String userId, HttpServletRequest request) {
        log.info("Access user:{}, url:{}, method:{}", userId, request.getServletPath(), request.getMethod());
        // 用户是否有某些角色
        List<Long> roleIds = userRoleService.queryRoleIdByUserId(Long.parseLong(userId));
        if (CollectionUtils.isEmpty(roleIds)) {
            return Boolean.FALSE;
        }
        // 用户是否有某些菜单权限
        List<Menu> menus = menuService.queryByRoleIds(roleIds);
        if (hasMenuPermission(menus, request.getServletPath())) {
            return Boolean.TRUE;
        }
        // 用户是否有某些资源的权限
        return resourceService.matchUserResources(roleIds, request);
    }

    /**
     * 判断用户是否有某菜单的权限，通过对比菜单href和当前url
     */
    private boolean hasMenuPermission(List<Menu> menus, String url) {
        if (CollectionUtils.isEmpty(menus)) {
            return Boolean.FALSE;
        }
        for (Menu menu : menus) {
            if (StringUtils.isNotBlank(menu.getHref()) && StringUtils.startsWith(url, menu.getHref())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public PermissionDTO getByUserId(Long userId) {
        log.info("[getByUserId]参数:{}", userId);
        List<Long> roleIds = userRoleService.queryRoleIdByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return PermissionDTO.builder().menuList(Collections.emptyList()).resourceList(Collections.emptyList())
                                .build();
        }
        List<MenuNodeDTO> menuList = convertMenus(roleIds);
        Collections.sort(menuList);
        List<ResourceDTO> resourceList = convertResources(roleIds);
        return PermissionDTO.builder().menuList(menuList).resourceList(resourceList).build();
    }

    private List<ResourceDTO> convertResources(List<Long> roleIds) {
        List<Resource> resourceList = resourceService.queryByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(resourceList)) {
            return Collections.emptyList();
        }
        return ConvertUtil.convertList(resourceList, ResourceDTO.class);
    }

    private List<MenuNodeDTO> convertMenus(List<Long> roleIds) {
        List<Menu> menus = menuService.queryByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        List<Menu> topLevelMenus = menus.stream()
                                        .filter(menu -> Objects.equals(menu.getLevel(), MenuConstant.TOP_LEVEL))
                                        .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(topLevelMenus)) {
            return Collections.emptyList();
        }
        return topLevelMenus.stream().map(menu -> menuService.fillMenuNode(menu)).collect(Collectors.toList());
    }
}
