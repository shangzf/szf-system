package com.shangzf.message.remote;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.message.api.dto.MessageDTO;
import com.shangzf.message.api.dto.param.MessageParam;
import com.shangzf.message.entity.Message;
import com.shangzf.message.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageRemoteService {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/page")
    public DataGrid<MessageDTO> getPage(MessageParam messageParam) {
        Page<Message> messagePage = messageService.getPage(messageParam);
        List<MessageDTO> dtoList = ConvertUtil.convertList(messagePage.getRecords(), MessageDTO.class);
        return new DataGrid<>(dtoList, messagePage.getTotal(), messagePage.getSize(), messagePage.getCurrent());
    }

    @PutMapping("/read")
    public Boolean read(@RequestBody Long id) {
        return messageService.read(id);
    }

    @PutMapping("/read/add")
    public Boolean readAll() {
        return messageService.readAll();
    }

    @GetMapping("/unread")
    public Integer unreadCount() {
        return messageService.unreadCount();
    }
}
