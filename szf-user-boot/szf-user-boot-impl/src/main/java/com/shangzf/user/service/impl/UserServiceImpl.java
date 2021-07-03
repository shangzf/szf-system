package com.shangzf.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.user.api.dto.param.UserParam;
import com.shangzf.user.entity.User;
import com.shangzf.user.mapper.UserMapper;
import com.shangzf.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<User> getUsersByPage(UserParam param) {
        log.info("[getRolesByPage]参数: {}", JSON.toJSONString(param));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Objects.nonNull(param.getUserId()), User::getId, param.getUserId())
               .like(StringUtils.isNotBlank(param.getPhone()), User::getPhone, param.getPhone())
               .and(Objects.nonNull(param.getStartCreateTime()) || Objects.nonNull(param.getEndCreateTime()), w -> w
                       .ge(Objects.nonNull(param.getStartCreateTime()), User::getCreateTime, param.getStartCreateTime())
                       .le(Objects.nonNull(param.getEndCreateTime()), User::getCreateTime, param.getEndCreateTime()))
               .orderByDesc(User::getCreateTime);
        return this.page(new Page<>(param.getCurrent(), param.getSize()), wrapper);
    }

    @Override
    public User getUserById(Long id) {
        log.info("[getUserById]参数: {}", id);
        return this.getById(id);
    }

    @Override
    public User getUserByPhone(String phone) {
        log.info("[getUserByPhone]参数: {}", phone);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getPhone, phone).eq(User::getDeleted, Boolean.FALSE);
        List<User> userList = this.list(wrapper);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Boolean saveUser(User user) {
        log.info("[saveUser]参数: {}", JSON.toJSONString(user));
        return this.save(user);
    }

    @Override
    public Boolean updateUser(User user) {
        log.info("[updateUser]参数: {}", JSON.toJSONString(user));
        return this.updateById(user);
    }
}
