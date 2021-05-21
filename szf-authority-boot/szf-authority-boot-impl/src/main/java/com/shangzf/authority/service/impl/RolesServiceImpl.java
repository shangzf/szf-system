package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.mapper.RolesMapper;
import com.shangzf.authority.service.IRoleMenuService;
import com.shangzf.authority.service.IRoleResourceService;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.authority.service.IUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-20
 */
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
        userRoleService.removeByRoleId(id);
        roleMenuService.removeByRoleId(id);
        roleResourceService.removeByRoleId(id);
        return this.removeById(id);
    }

    @Override
    public List<Roles> getRolesByUserId(Long userId) {
        List<Long> roleIds = userRoleService.queryRoleIdByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return this.listByIds(roleIds);
    }

    @Override
    public List<Roles> getAll() {
        return this.list();
    }
}
