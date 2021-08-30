package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AuthorityDTO;
import com.shangzf.authority.api.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "szf-authority-boot", path = "/auth")
public interface IAuthenticationRemoteService {

    @PostMapping("/authenticate")
    boolean authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @SpringQueryMap AuthorityDTO dto);

    @GetMapping("/user/{userId}")
    PermissionDTO getByUserId(@PathVariable("userId") Long userId);
}
