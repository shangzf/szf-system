package com.shangzf.user.remote;

import com.shangzf.user.api.dto.WeixinDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/weixin")
public class WeixinRemoteService {

    @GetMapping("/user/{userId}")
    public WeixinDTO getInfoByUserId(@PathVariable("userId") String userId) {
        return null;
    }

    @GetMapping("/open/{openId}")
    public WeixinDTO getInfoByOpenId(@PathVariable("openId") String openId) {
        return null;
    }

    @GetMapping("/union/{unionId}")
    public WeixinDTO getInfoByUnionId(@PathVariable("unionId") String unionId) {
        return null;
    }
}
