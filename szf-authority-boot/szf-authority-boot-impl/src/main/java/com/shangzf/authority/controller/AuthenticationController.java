package com.shangzf.authority.controller;

import com.shangzf.authority.api.dto.PermissionDTO;
import com.shangzf.authority.service.IAuthenticationService;
import com.shangzf.authority.wapper.HttpServletRequestAuthWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "AuthenticationController", description = "权限验证")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Operation(summary = "权限认证", description = "根据用户userId，访问的url和method判断用户是否有权限访问", parameters = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.QUERY, required = true), @Parameter(name = "url", description = "请求的URL", in = ParameterIn.QUERY, required = true), @Parameter(name = "method", description = "请求方法", in = ParameterIn.QUERY, required = true),})
    @PostMapping(value = "/authenticate")
    public boolean authenticate(@RequestParam String userId, @RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        return authenticationService.authenticate(userId, new HttpServletRequestAuthWrapper(request, url, method));
    }

    @Operation(summary = "获取用户菜单和资源权限列表", description = "获取用户菜单和资源权限列表", parameters = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.QUERY, required = true)})
    @GetMapping("/user")
    public PermissionDTO getByUserId(@RequestParam("userId") Long userId) {
        return authenticationService.getByUserId(userId);
    }
}
