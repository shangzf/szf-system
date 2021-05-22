package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szf-authority-boot", path = "/auth")
public interface IAuthenticationRemote {

    boolean authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestParam("userId") String userId, @RequestParam("url") String url, @RequestParam("method") String method);

    @GetMapping("/user/{userId}")
    PermissionDTO getByUserId(@PathVariable("userId") Long userId);
}
