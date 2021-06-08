package com.shangzf.message.api.remote;

import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.message.api.dto.MessageDTO;
import com.shangzf.message.api.dto.param.MessageParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "szf-message-boot", path = "/message")
public interface IMessageRemoteService {

    @GetMapping("/page")
    DataGrid<MessageDTO> getPage(@SpringQueryMap MessageParam messageParam);

    @PutMapping("/read")
    Boolean read(@RequestBody Long id);

    @PutMapping("/read/add")
    Boolean readAll();

    @GetMapping("/unread")
    Integer unreadCount();
}
