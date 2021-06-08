package com.shangzf.user.service.impl;

import com.shangzf.user.entity.PhoneVerificationCode;
import com.shangzf.user.mapper.PhoneVerificationCodeMapper;
import com.shangzf.user.service.IPhoneVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 验证码 服务实现类
 * </p>
 */
@Service
public class PhoneVerificationCodeServiceImpl extends ServiceImpl<PhoneVerificationCodeMapper, PhoneVerificationCode> implements IPhoneVerificationCodeService {

}
