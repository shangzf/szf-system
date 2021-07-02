package com.shangzf.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shangzf.user.api.dto.param.UserParam;
import com.shangzf.user.entity.User;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    Page<User> getUsersByPage(UserParam param);

    User getUserById(Long id);

    User getUserByPhone(String phone);

    Boolean saveUser(User user);

    Boolean updateUser(User user);
}
