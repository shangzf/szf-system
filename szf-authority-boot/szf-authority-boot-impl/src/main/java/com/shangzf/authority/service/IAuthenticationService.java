package com.shangzf.authority.service;

import com.shangzf.authority.api.dto.PermissionDTO;

public interface IAuthenticationService {
    PermissionDTO getByUserId(Long userId);
}
