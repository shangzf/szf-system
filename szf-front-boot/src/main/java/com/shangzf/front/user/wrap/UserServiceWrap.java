package com.shangzf.front.user.wrap;

import com.shangzf.common.pojo.vo.ResultResponse;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.front.user.dto.LoginDTO;
import com.shangzf.front.user.dto.TokenDTO;
import com.shangzf.front.user.service.IUserService;
import com.shangzf.front.user.vo.TokenVO;

import java.util.Objects;

public class UserServiceWrap {

    public static ResultResponse<?> createAuthToken(IUserService userService, LoginDTO dto) {
        TokenDTO tokenDTO = userService.createAuthToken(dto);
        if (Objects.nonNull(tokenDTO.getCode())) {
            return ResultResponse.builder(new TokenVO()).code(tokenDTO.getCode()).message(tokenDTO.getMessage())
                                 .build();
        }
        TokenVO vo = ConvertUtil.convert(tokenDTO, TokenVO.class);
        return ResultResponse.successOfData(vo);
    }
}
