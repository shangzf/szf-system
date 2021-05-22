package com.shangzf.authority.remote;

import com.shangzf.authority.api.dto.PermissionDTO;
import com.shangzf.authority.service.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限验证")
@RestController
@RequestMapping("/auth")
public class AuthenticationRemote {

    @Autowired
    private IAuthenticationService authenticationService;

    @Operation(summary = "获取用户菜单和资源权限列表")
    @GetMapping("/user/{userId}")
    public PermissionDTO getByUserId(@PathVariable("userId") Long userId) {
        return authenticationService.getByUserId(userId);
    }
}
