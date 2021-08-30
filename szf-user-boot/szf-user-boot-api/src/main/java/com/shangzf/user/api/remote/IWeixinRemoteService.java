package com.shangzf.user.api.remote;

import com.shangzf.user.api.dto.WeixinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "szf-user-boot", path = "/user/weixin")
public interface IWeixinRemoteService {

    @GetMapping("/user/{userId}")
    WeixinDTO getInfoByUserId(@PathVariable("userId") String userId);

    @GetMapping("/open/{openId}")
    WeixinDTO getInfoByOpenId(@PathVariable("openId") String openId);

    @GetMapping("/union/{unionId}")
    WeixinDTO getInfoByUnionId(@PathVariable("unionId") String unionId);

    @PostMapping("/save")
    Boolean save(@RequestBody WeixinDTO dto);

    @PutMapping("/update")
    Boolean update(@RequestBody WeixinDTO dto);

    @PatchMapping("/bind")
    Boolean bind(@RequestBody WeixinDTO dto);

    @PatchMapping("/unbind/{userId}")
    Boolean unbind(@PathVariable("userId") String userId);
}
