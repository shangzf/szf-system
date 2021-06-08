package com.shangzf.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangzf.common.user.UserManager;
import com.shangzf.message.api.dto.param.MessageParam;
import com.shangzf.message.entity.Message;
import com.shangzf.message.mapper.MessageMapper;
import com.shangzf.message.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息 服务实现类
 * </p>
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public Page<Message> getPage(MessageParam messageParam) {
        log.info("[getPage]参数: {}", JSON.toJSONString(messageParam));
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Message::getUserId, UserManager.getUserId())
               .like(Message::getTheme, messageParam.getQuery()).orderByDesc(Message::getCreateTime);
        return this.page(new Page<>(messageParam.getCurrent(), messageParam.getSize()), wrapper);
    }

    @Override
    public Boolean read(Long id) {
        log.info("[read]参数: {}", id);
        Message message = new Message();
        message.setId(id);
        message.setUnread(Boolean.FALSE);
        return this.updateById(message);
    }

    @Override
    public Boolean readAll() {
        log.info("[readAll]参数: {}", UserManager.getUserId());
        UpdateWrapper<Message> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(Message::getUnread, Boolean.FALSE).eq(Message::getUserId, UserManager.getUserId())
               .eq(Message::getUnread, Boolean.TRUE);
        return this.update(wrapper);
    }

    @Override
    public Integer unreadCount() {
        log.info("[unreadCount]参数: {}", UserManager.getUserId());
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Message::getUserId, UserManager.getUserId()).eq(Message::getUnread, Boolean.TRUE);
        return this.count(wrapper);
    }
}
