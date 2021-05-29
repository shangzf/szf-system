package com.shangzf.user.api.remote;

import com.shangzf.user.api.dto.WeixinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "szf-user-boot", path = "/user/weixin")
public interface IWeixinRemoteService {

    @GetMapping("/user/{userId}")
    WeixinDTO getInfoByUserId(@PathVariable("userId") String userId);

    @GetMapping("/open/{openId}")
    WeixinDTO getInfoByOpenId(@PathVariable("openId") String openId);

    @GetMapping("/union/{unionId}")
    WeixinDTO getInfoByUnionId(@PathVariable("unionId") String unionId);
}
