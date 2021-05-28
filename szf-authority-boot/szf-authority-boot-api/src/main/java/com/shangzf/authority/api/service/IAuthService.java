package com.shangzf.authority.api.service;

import com.shangzf.authority.api.dto.AuthorityExtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface IAuthService {

    /**
     * 调用签权服务，判断用户是否有权限
     */
    boolean authenticate(AuthorityExtDTO dto);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     */
    boolean ignoreAuthentication(String url);

    /**
     * 从认证信息中提取jwt token 对象
     */
    Jws<Claims> getJwt(String jwtToken);
}
