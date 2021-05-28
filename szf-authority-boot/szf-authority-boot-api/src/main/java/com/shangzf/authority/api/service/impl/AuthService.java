package com.shangzf.authority.api.service.impl;

import com.shangzf.authority.api.dto.AuthorityExtDTO;
import com.shangzf.authority.api.remote.IAuthenticationRemoteService;
import com.shangzf.authority.api.service.IAuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
    private IAuthenticationRemoteService authenticationRemote;

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
    public boolean authenticate(AuthorityExtDTO dto) {
        return authenticationRemote.authenticate(dto.getAuthentication(), dto);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(StringUtils.split(this.ignoreUrls, ","))
                     .anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public Jws<Claims> getJwt(String jwtToken) {
        if (jwtToken.startsWith(BEARER)) {
            jwtToken = StringUtils.substring(jwtToken, BEARER.length());
        }
        return Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(jwtToken);
    }
}
