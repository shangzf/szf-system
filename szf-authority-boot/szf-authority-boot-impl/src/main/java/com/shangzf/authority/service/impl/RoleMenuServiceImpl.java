package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangzf.authority.entity.RoleMenu;
import com.shangzf.authority.mapper.RoleMenuMapper;
import com.shangzf.authority.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色-菜单 服务实现类
 * </p>
 *
 * @author
 * @since 2021-05-20
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Long roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, roleId);
        return this.remove(queryWrapper);
    }
}
