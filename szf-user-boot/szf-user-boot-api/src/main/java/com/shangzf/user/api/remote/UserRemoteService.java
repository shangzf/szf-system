package com.shangzf.user.api.remote;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.user.api.dto.PasswordDTO;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.param.UserQueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "szf-user-boot",path = "/user")
public interface UserRemoteService {

    @GetMapping("/page")
    Page<UserDTO> getUserPage(@SpringQueryMap UserQueryParam queryParam);

    @GetMapping("/{id}")
    UserDTO getById(@PathVariable("id") Long id);

    @GetMapping("/phone/{phone}")
    UserDTO getByPhone(@PathVariable("phone") String phone);

    @GetMapping("/register/{phone}")
    Boolean checkRegister(@PathVariable("phone") String phone);

    @PostMapping("/")
    UserDTO save(@RequestBody UserDTO dto);

    @PutMapping("/")
    Boolean update(@RequestBody UserDTO dto);

    @GetMapping("/updatePwd/{id}")
    Boolean checkUpdatePassword(@PathVariable("id") Long id);

    @PutMapping("/pwd")
    Boolean editPassword(@RequestBody PasswordDTO dto);


}
