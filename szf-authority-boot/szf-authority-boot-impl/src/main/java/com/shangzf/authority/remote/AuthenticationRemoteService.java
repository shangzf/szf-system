package com.shangzf.authority.remote;

import com.shangzf.authority.api.dto.PermissionDTO;
import com.shangzf.authority.service.IAuthenticationService;
import com.shangzf.authority.wapper.HttpServletRequestAuthWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "权限验证")
@RestController
@RequestMapping("/auth")
public class AuthenticationRemoteService {

    @Autowired
    private IAuthenticationService authenticationService;

    @Operation(summary = "根据用户userId，访问的url和method判断用户是否有权限访问")
    @PostMapping(value = "/authenticate")
    public boolean authenticate(@RequestParam String userId, @RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        return authenticationService.authenticate(userId, new HttpServletRequestAuthWrapper(request, url, method));
    }

    @Operation(summary = "获取用户菜单和资源权限列表")
    @GetMapping("/user/{userId}")
    public PermissionDTO getByUserId(@PathVariable("userId") Long userId) {
        return authenticationService.getByUserId(userId);
    }
}
