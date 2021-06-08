package com.shangzf.front.user.controller;

import com.shangzf.common.constant.AuthTypeConstant;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import com.shangzf.front.user.dto.LoginDTO;
import com.shangzf.front.user.service.IUserService;
import com.shangzf.front.user.code.UserCode;
import com.shangzf.front.user.wrap.UserServiceWrap;
import com.shangzf.user.api.remote.IUserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRemoteService userRemoteService;
    @Autowired
    private IUserService userService;

    @PostMapping("/login/code")
    public ResultResponse<?> loginCode(@Validated(LoginDTO.CodeGroup.class) @RequestBody LoginDTO dto) {
        Boolean register = userRemoteService.checkRegister(dto.getPhone());
        if (!register) {

        }
        dto.setType(AuthTypeConstant.MOBILE);
        return UserServiceWrap.createAuthToken(userService, dto);
    }

    @PostMapping("/login")
    public ResultResponse<?> login(@Validated(LoginDTO.PasswordGroup.class) @RequestBody LoginDTO dto) {
        Boolean register = userRemoteService.checkRegister(dto.getPhone());
        if (!register) {
            return ResultResponse.fail(UserCode.UNREGISTERED);
        }
        dto.setType(AuthTypeConstant.PASSWORD);
        return UserServiceWrap.createAuthToken(userService, dto);
    }

    @PostMapping("/logout")
    public ResultResponse<?> logout(){
        return ResultResponse.success();
    }
}
