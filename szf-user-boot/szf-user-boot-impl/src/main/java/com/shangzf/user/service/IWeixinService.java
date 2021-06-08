package com.shangzf.user.service;

import com.shangzf.user.entity.Weixin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 微信绑定信息 服务类
 * </p>
 */
public interface IWeixinService extends IService<Weixin> {

    Weixin getInfoByUserId(Long userId);

    Weixin getInfoByOpenId(String openId);

    Weixin getInfoByUnionId(String unionId);

    Boolean bind(Weixin weixin);

    Boolean unbind(Long userId);
}
