package com.shangzf.authority.service;

import com.shangzf.authority.api.dto.PermissionDTO;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

    boolean authenticate(String userId, HttpServletRequest request);

    PermissionDTO getByUserId(Long userId);
}
