package com.shangzf.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shangzf.message.api.dto.param.MessageParam;
import com.shangzf.message.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 消息 服务类
 * </p>
 */
public interface IMessageService extends IService<Message> {

    Page<Message> getPage(MessageParam messageParam);

    Boolean read(@RequestBody Long id);

    Boolean readAll();

    Integer unreadCount();

}
