package com.shangzf.authority.mapper;

import com.shangzf.authority.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getByRoleId(@Param("roleId") Long roleId);

    List<Menu> getByRoleIds(@Param("roleIds") List<Long> roleIds);
}
