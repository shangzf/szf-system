package com.shangzf.front.user.service;

import com.alibaba.fastjson.JSON;
import com.shangzf.common.vo.response.ResultResponse;
import com.shangzf.front.user.vo.LoginVO;
import com.shangzf.front.user.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserService {

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

    public ResultResponse createAuthToken(LoginVO vo) {
        log.info("phone:{}, password:{}, scope:{}, grantType:{}, clientId:{}, clientSecret:{}", vo.getPhone(), vo
                .getPassword(), scope, grantType, clientId, clientSecret);
        String token = this.oAuthRemoteService
                .createToken(vo.getPhone(), vo.getPassword(), scope, grantType, clientId, clientSecret, vo.getType());
        TokenVO tokenVO = JSON.parseObject(token, TokenVO.class);
        if (Objects.nonNull(tokenVO.getCode())) {
            log.info("phone:{}, jwt token is null, token:{}", vo.getPhone(), token);
            return ResultResponse.builder(null).code(tokenVO.getCode()).message(tokenVO.getMessage()).build();
        }
        return ResultResponse.success(tokenVO);
    }
}
