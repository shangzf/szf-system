package com.shangzf.front.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.shangzf.front.user.dto.LoginDTO;
import com.shangzf.front.user.dto.TokenDTO;
import com.shangzf.front.user.service.IUserService;
import com.shangzf.front.user.service.OAuthRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private OAuthRemoteService oAuthRemoteService;
    @Value("${spring.oauth.client_id}")
    private String clientId;
    @Value("${spring.oauth.client_secret}")
    private String clientSecret;
    @Value("${spring.oauth.scope}")
    private String scope;
    @Value("${spring.oauth.grant_type}")
    private String grantType;
    @Value("${spring.oauth.refresh_grant_type}")
    private String refreshGrantType;

    @Override
    public TokenDTO createAuthToken(LoginDTO dto) {
        log.info("phone:{}, password:{}, scope:{}, grantType:{}, clientId:{}, clientSecret:{}", dto.getPhone(), dto
                .getPassword(), scope, grantType, clientId, clientSecret);
        String token = this.oAuthRemoteService
                .createToken(dto.getPhone(), dto.getPassword(), scope, grantType, clientId, clientSecret, dto.getType());
        return JSON.parseObject(token, TokenDTO.class);
    }
}
