package com.shangzf.front.message.controller;

import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.front.message.vo.MessageVO;
import com.shangzf.message.api.dto.MessageDTO;
import com.shangzf.message.api.dto.param.MessageParam;
import com.shangzf.message.api.remote.IMessageRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageRemoteService messageRemoteService;

    @GetMapping("/page")
    public ResultResponse<DataGrid<MessageVO>> list(MessageParam param) {
        DataGrid<MessageDTO> dtoDataGrid = messageRemoteService.getPage(param);
        List<MessageVO> voList = ConvertUtil.convertList(dtoDataGrid.getRecords(), MessageVO.class);
        return ResultResponse.successOfData(new DataGrid<>(voList, dtoDataGrid));
    }

    @PutMapping("/read")
    public Boolean read(@RequestBody Long id) {
        return messageRemoteService.read(id);
    }

    @PutMapping("/read/add")
    public Boolean readAll() {
       return messageRemoteService.readAll();
    }

    @GetMapping("/unread")
    public Integer unreadCount() {
        return messageRemoteService.unreadCount();
    }
}
