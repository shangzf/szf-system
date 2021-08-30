package com.shangzf.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.entity.Resource;

import javax.servlet.http.HttpServletRequest;
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

    void loadResource();

    /**
     * 根据请求url匹配资源url。能够在系统资源列表中匹配到资源的url，表明有权限访问
     */
    boolean matchRequestUrl(HttpServletRequest authRequest);

    List<Resource> getByCategoryId(Long categoryId);

    List<Resource> getByRoleId(Long roleId);

    /**
     * 给角色分配资源
     */
    boolean allocateRoleResources(AllocateRoleResourceDTO dto);

    List<Resource> queryByRoleIds(List<Long> roleIds);

    boolean matchUserResources(List<Long> roleIds, HttpServletRequest request);

    Boolean saveResource(Resource resource);

    Boolean updateResource(Resource resource);
}
