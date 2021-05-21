package com.shangzf.front.user.controller;

import com.shangzf.common.vo.constant.AuthTypeConstant;
import com.shangzf.common.vo.response.ResultResponse;
import com.shangzf.common.vo.response.ResultResponseData;
import com.shangzf.front.user.response.UserCode;
import com.shangzf.front.user.service.UserService;
import com.shangzf.front.user.vo.LoginVO;
import com.shangzf.front.user.vo.group.CodeGroup;
import com.shangzf.front.user.vo.group.PasswordGroup;
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
    private UserService userService;

    @PostMapping("/login/code")
    public ResultResponseData loginCode(@Validated(CodeGroup.class) @RequestBody LoginVO vo) {
        Boolean register = userRemoteService.checkRegister(vo.getPhone());
        if (!register) {

        }
        vo.setType(AuthTypeConstant.MOBILE);
        return userService.createAuthToken(vo);
    }

    @PostMapping("/login")
    public ResultResponseData login(@Validated(PasswordGroup.class) @RequestBody LoginVO vo) {
        Boolean register = userRemoteService.checkRegister(vo.getPhone());
        if (!register) {
            return ResultResponseData.fail(UserCode.UNREGISTERED, null);
        }
        vo.setType(AuthTypeConstant.PASSWORD);
        return userService.createAuthToken(vo);
    }

    @PostMapping("/logout")
    public ResultResponse logout(){
        return ResultResponse.success();
    }
}
