package com.shangzf.user.service.impl;

import com.shangzf.user.entity.User;
import com.shangzf.user.mapper.UserMapper;
import com.shangzf.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-05-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
