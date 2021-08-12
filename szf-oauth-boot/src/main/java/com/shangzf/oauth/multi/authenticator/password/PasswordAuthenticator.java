package com.shangzf.oauth.multi.authenticator.password;

import com.shangzf.common.constant.AuthTypeConstant;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.oauth.entity.UserOAuth;
import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.oauth.multi.authenticator.AbstractMultiAuthenticator;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.remote.IUserRemoteService;
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
    public UserOAuth authenticate(MultiAuthentication multiAuthentication) {
        UserDTO userDTO = userRemoteService.getByPhone(multiAuthentication.getUsername());
        return ConvertUtil.convert(userDTO, UserOAuth.class);
    }

    @Override
    public boolean support(MultiAuthentication multiAuthentication) {
        return StringUtils.contains(AuthTypeConstant.PASSWORD, multiAuthentication.getAuthType()) || StringUtils
                .isBlank(multiAuthentication.getAuthType());
    }
}
