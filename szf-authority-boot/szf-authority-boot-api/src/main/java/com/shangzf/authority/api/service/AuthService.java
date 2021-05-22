package com.shangzf.authority.api.service;

import com.shangzf.authority.api.remote.IAuthenticationRemote;
import com.shangzf.common.vo.response.ResultResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class AuthService implements IAuthService {

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    @Autowired
    private IAuthenticationRemote authenticationRemote;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey:123456}")
    private String signingKey;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith:/oauth,/open}")
    private String ignoreUrls;


    @Override
    public ResultResponseData<Boolean> authenticate(String authentication, String userId, String url, String method) {
        boolean result = authenticationRemote.authenticate(authentication, userId, url, method);
        return ResultResponseData.success(result);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(StringUtils.split(this.ignoreUrls, ","))
                     .anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }
}
