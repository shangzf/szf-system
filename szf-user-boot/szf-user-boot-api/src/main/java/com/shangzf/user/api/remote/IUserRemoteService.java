package com.shangzf.user.api.remote;

import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.user.api.dto.ChangePasswordDTO;
import com.shangzf.user.api.dto.PasswordDTO;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.dto.param.UserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szf-user-boot",path = "/user")
public interface IUserRemoteService {

    @GetMapping("/page")
    DataGrid<UserDTO> getUserPage(@SpringQueryMap UserParam queryParam);

    @GetMapping("/")
    UserDTO getById(@RequestParam("id") Long id);

    @GetMapping("/phone")
    UserDTO getByPhone(@RequestParam("phone") String phone);

    @GetMapping("/register")
    Boolean checkRegister(@RequestParam("phone") String phone);

    @PostMapping("/")
    Boolean save(@RequestBody UserDTO dto);

    @PutMapping("/")
    Boolean update(@RequestBody UserDTO dto);

    @GetMapping("/pwd/check")
    Boolean checkPassword(@RequestParam("id") Long id);

    @PutMapping("/pwd/update")
    Boolean updatePassword(@RequestBody ChangePasswordDTO dto);

    @PutMapping("/pwd/save")
    Boolean savePassword(@RequestBody PasswordDTO dto);

}
