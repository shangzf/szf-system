package com.shangzf.oauth.multi.authenticator;

import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.user.api.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;

public interface MultiAuthenticator {

    /**
     * 认证
     */
    UserDTO authenticate(MultiAuthentication multiAuthentication);

    /**
     * 进行预处理
     */
    void prepare(MultiAuthentication multiAuthentication, HttpServletResponse response);

    /**
     * 判断是否支持集成认证类型
     */
    boolean support(MultiAuthentication multiAuthentication);

    /**
     * 认证结束后执行
     */
    void complete(MultiAuthentication multiAuthentication);
}
