package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AuthorityDTO;
import com.shangzf.authority.api.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szf-authority-boot", path = "/auth")
public interface IAuthenticationRemoteService {

    @GetMapping("/authenticate")
    boolean authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @SpringQueryMap AuthorityDTO dto);

    @GetMapping("/user")
    PermissionDTO getByUserId(@RequestParam("userId") Long userId);
}
