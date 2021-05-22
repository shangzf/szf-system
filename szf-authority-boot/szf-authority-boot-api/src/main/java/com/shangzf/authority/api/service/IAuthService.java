package com.shangzf.authority.api.service;

import com.shangzf.common.vo.response.ResultResponseData;

public interface IAuthService {

    /**
     * 调用签权服务，判断用户是否有权限
     */
    ResultResponseData<Boolean> authenticate(String authentication, String userId, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     */
    boolean ignoreAuthentication(String url);
}
