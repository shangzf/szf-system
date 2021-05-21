package com.shangzf.authority.service.impl;

import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.mapper.RolesMapper;
import com.shangzf.authority.service.IRoleMenuService;
import com.shangzf.authority.service.IRoleResourceService;
import com.shangzf.authority.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
