package com.shangzf.oauth.multi.authenticator.password;

import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.oauth.multi.authenticator.AbstractMultiAuthenticator;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.remote.IUserRemoteService;
import com.shangzf.common.vo.constant.AuthTypeConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PasswordAuthenticator extends AbstractMultiAuthenticator {

    @Autowired
    private IUserRemoteService userRemoteService;

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
