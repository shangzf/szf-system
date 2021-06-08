package com.shangzf.user.api.remote;

import com.shangzf.user.api.dto.WeixinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "szf-user-boot", path = "/user/weixin")
public interface IWeixinRemoteService {

    @GetMapping("/user")
    WeixinDTO getInfoByUserId(@RequestParam("userId") String userId);

    @GetMapping("/open")
    WeixinDTO getInfoByOpenId(@RequestParam("openId") String openId);

    @GetMapping("/union")
    WeixinDTO getInfoByUnionId(@RequestParam("unionId") String unionId);

    @PostMapping("/save")
    Boolean save(@RequestBody WeixinDTO dto);

    @PutMapping("/update")
    Boolean update(@RequestBody WeixinDTO dto);

    @PatchMapping("/bind")
    Boolean bind(@RequestBody WeixinDTO dto);

    @PatchMapping("/unbind")
    Boolean unbind(@RequestParam("userId") String userId);
}
