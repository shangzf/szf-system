package com.shangzf.oauth.oauth2.enhancer;

import com.google.common.collect.Maps;
import com.shangzf.common.vo.constant.AuthenticationConstant;
import com.shangzf.oauth.entity.UserJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;
import java.util.Optional;

/**
 * 自定义token携带内容
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionInfo = Maps.newHashMap();
        additionInfo.put(AuthenticationConstant.USER_NAME, authentication.getName());
        try {
            UserJwt userJwt = (UserJwt) authentication.getPrincipal();
            Optional.ofNullable(userJwt).ifPresent(userJwt1 -> {
                additionInfo.put(AuthenticationConstant.USER_ID, userJwt.getId());
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User name: {}", authentication.getName());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionInfo);
        return accessToken;
    }
}
