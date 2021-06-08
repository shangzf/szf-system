package com.shangzf.front.user.service;

import com.shangzf.front.user.dto.LoginDTO;
import com.shangzf.front.user.dto.TokenDTO;

public interface IUserService {

    TokenDTO createAuthToken(LoginDTO dto);
}
