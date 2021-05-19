package com.shangzf.front.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szf-oauth-boot")
public interface OAuthRemoteService {

    @PostMapping("/oauth/token")
    String createToken(@RequestParam("username") String phone, @RequestParam("password") String password, @RequestParam("scope") String scope, @RequestParam("grant_type") String grantType, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret, @RequestParam(value = "auth_type", required = false) String authType);

    @PostMapping("/oauth/token")
    String refreshToken(@RequestParam("refresh_token") String refreshToken, @RequestParam("grant_type") String grantType, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret);

}
