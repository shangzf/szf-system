package com.shangzf.oauth.multi.authenticator.password;

import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.oauth.multi.authenticator.AbstractMultiAuthenticator;
import com.shangzf.user.dto.UserDTO;
import com.shangzf.user.remote.UserRemoteService;
import com.shangzf.vo.constant.AuthTypeConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordAuthenticator extends AbstractMultiAuthenticator {

    @Autowired
    private UserRemoteService userRemoteService;

    @Override
    public UserDTO authenticate(MultiAuthentication multiAuthentication) {
        return userRemoteService.getByPhone(multiAuthentication.getUsername());
    }

    @Override
    public boolean support(MultiAuthentication multiAuthentication) {
        return StringUtils.contains(AuthTypeConstant.PASSWORD, multiAuthentication.getAuthType()) || StringUtils
                .isBlank(multiAuthentication.getAuthType());
    }
}
