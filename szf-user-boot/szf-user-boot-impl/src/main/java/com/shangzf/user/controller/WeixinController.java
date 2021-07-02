package com.shangzf.user.controller;

import com.shangzf.common.util.ConvertUtil;
import com.shangzf.user.api.dto.WeixinDTO;
import com.shangzf.user.entity.Weixin;
import com.shangzf.user.service.IWeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/weixin")
public class WeixinController {

    @Autowired
    private IWeixinService weixinService;

    @GetMapping("/user")
    public WeixinDTO getInfoByUserId(@RequestParam("userId") Long userId) {
        Weixin weixin = weixinService.getInfoByUserId(userId);
        return ConvertUtil.convert(weixin, WeixinDTO.class);
    }

    @GetMapping("/open")
    public WeixinDTO getInfoByOpenId(@RequestParam("openId") String openId) {
        Weixin weixin = weixinService.getInfoByOpenId(openId);
        return ConvertUtil.convert(weixin, WeixinDTO.class);
    }

    @GetMapping("/union")
    public WeixinDTO getInfoByUnionId(@RequestParam("unionId") String unionId) {
        Weixin weixin = weixinService.getInfoByUnionId(unionId);
        return ConvertUtil.convert(weixin, WeixinDTO.class);
    }

    @PostMapping("/save")
    public Boolean save(@RequestBody WeixinDTO dto){
        Weixin weixin = ConvertUtil.convert(dto, Weixin.class);
        return weixinService.save(weixin);
    }

    @PutMapping("/update")
    public Boolean update(@RequestBody WeixinDTO dto){
        Weixin weixin = ConvertUtil.convert(dto, Weixin.class);
        return weixinService.updateById(weixin);
    }

    @PostMapping("/bind")
    public Boolean bind(@RequestBody WeixinDTO dto){
        Weixin weixin = ConvertUtil.convert(dto, Weixin.class);
        return weixinService.bind(weixin);
    }

    @PutMapping("/unbind")
    public Boolean unbind(@RequestParam("userId") Long userId){
        return weixinService.unbind(userId);
    }
}
