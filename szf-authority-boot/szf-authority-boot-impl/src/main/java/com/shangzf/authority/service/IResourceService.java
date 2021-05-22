package com.shangzf.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.entity.Resource;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author 
 * @since 2021-05-20
 */
public interface IResourceService extends IService<Resource> {

    List<Resource> getByCategoryId(Long categoryId);

    List<Resource> getByRoleId(Long roleId);

    /**
     * 给角色分配资源
     */
    boolean allocateRoleResources(AllocateRoleResourceDTO dto);

    List<Resource> queryByRoleIds(List<Long> roleIds);

}
