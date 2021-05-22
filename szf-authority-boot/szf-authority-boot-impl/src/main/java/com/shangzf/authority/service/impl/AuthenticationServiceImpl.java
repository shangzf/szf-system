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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IResourceService resourceService;

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
        List<Menu> menus = menuService.getByRoleIds(roleIds);
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
