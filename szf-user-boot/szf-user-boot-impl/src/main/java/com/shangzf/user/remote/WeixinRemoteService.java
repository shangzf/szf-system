package com.shangzf.user.remote;

import com.shangzf.user.api.dto.WeixinDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/weixin")
public class WeixinRemoteService {

    @GetMapping("/user")
    public WeixinDTO getInfoByUserId(@RequestParam("userId") String userId) {
        return null;
    }

    @GetMapping("/open")
    public WeixinDTO getInfoByOpenId(@RequestParam("openId") String openId) {
        return null;
    }

    @GetMapping("/union")
    public WeixinDTO getInfoByUnionId(@RequestParam("unionId") String unionId) {
        return null;
    }

    @PostMapping("/save")
    public Boolean save(@RequestBody WeixinDTO dto){
        return null;
    }

    @PutMapping("/update")
    public Boolean update(@RequestBody WeixinDTO dto){
        return null;
    }

    @PutMapping("/bind")
    public Boolean bind(@RequestBody WeixinDTO dto){
        return null;
    }

    @PutMapping("/unbind")
    public Boolean unbind(@RequestBody WeixinDTO dto){
        return null;
    }
}
