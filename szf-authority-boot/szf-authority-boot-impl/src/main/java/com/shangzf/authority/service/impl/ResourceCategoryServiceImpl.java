package com.shangzf.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.authority.entity.ResourceCategory;
import com.shangzf.authority.mapper.ResourceCategoryMapper;
import com.shangzf.authority.service.IResourceCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 资源类别 服务实现类
 * </p>
 */
@Slf4j
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements IResourceCategoryService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        log.info("[delete]参数:{}", id);
        return this.removeById(id);
    }
}
