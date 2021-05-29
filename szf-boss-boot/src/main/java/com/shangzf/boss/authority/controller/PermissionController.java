package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.PermissionDTO;
import com.shangzf.authority.api.remote.IAuthenticationRemoteService;
import com.shangzf.common.user.UserManager;
import com.shangzf.common.pojo.vo.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户权限")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IAuthenticationRemoteService authenticationRemote;

    @Operation(summary = "获取用户菜单和用户资源")
    @GetMapping("/user")
    public ResultResponse<PermissionDTO> getPermission(){
        Long userId = UserManager.getUserId();
        PermissionDTO dto = authenticationRemote.getByUserId(userId);
        return ResultResponse.successOfData(dto);
    }
}
