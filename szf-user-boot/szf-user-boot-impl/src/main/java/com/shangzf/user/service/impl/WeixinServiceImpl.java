package com.shangzf.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangzf.common.web.exception.ServiceException;
import com.shangzf.user.code.UserCode;
import com.shangzf.user.entity.Weixin;
import com.shangzf.user.mapper.WeixinMapper;
import com.shangzf.user.service.IWeixinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 微信绑定信息 服务实现类
 * </p>
 */
@Slf4j
@Service
public class WeixinServiceImpl extends ServiceImpl<WeixinMapper, Weixin> implements IWeixinService {

    @Override
    public Weixin getInfoByUserId(Long userId) {
        log.info("[getInfoByUserId]参数:{}", userId);
        QueryWrapper<Weixin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                    .eq(Weixin::getUserId, userId)
                    .eq(Weixin::getDeleted, Boolean.FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    public Weixin getInfoByOpenId(String openId) {
        log.info("[getInfoByOpenId]参数:{}", openId);
        QueryWrapper<Weixin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Weixin::getOpenId, openId).eq(Weixin::getDeleted, Boolean.FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    public Weixin getInfoByUnionId(String unionId) {
        log.info("[getInfoByUnionId]参数:{}", unionId);
        QueryWrapper<Weixin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                    .eq(Weixin::getUnionId, unionId)
                    .eq(Weixin::getDeleted, Boolean.FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean bind(Weixin weixin) {
        log.info("[bind]参数:{}", JSON.toJSONString(weixin));
        QueryWrapper<Weixin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                    .eq(Weixin::getUserId, weixin.getUserId())
                    .eq(Weixin::getUnionId, weixin.getUnionId())
                    .eq(Weixin::getDeleted, Boolean.FALSE);
        Weixin one = this.getOne(queryWrapper);
        if (Objects.nonNull(one)) {
            // 已经绑定
            return Boolean.TRUE;
        }
        // 用户ID是否绑定
        Weixin byUserId = this.getInfoByUserId(weixin.getUserId());
        if (Objects.nonNull(byUserId)) {
            // 用户已经绑定了其他unionId
            throw new ServiceException(UserCode.USER_ID_BIND);
        }
        // unionId是否绑定
        Weixin byUnionId = this.getInfoByUnionId(weixin.getUnionId());
        if (Objects.nonNull(byUnionId)) {
            // unionId已经绑定了其他用户
            throw new ServiceException(UserCode.UNION_ID_BIND);
        }
        return this.save(weixin);
    }

    @Override
    public Boolean unbind(Long userId) {
        log.info("[unbind]参数:{}", userId);
        Weixin byUserId = this.getInfoByUserId(userId);
        if (Objects.isNull(byUserId)) {
            return Boolean.TRUE;
        }
        byUserId.setDeleted(Boolean.TRUE);
        return this.updateById(byUserId);
    }
}
