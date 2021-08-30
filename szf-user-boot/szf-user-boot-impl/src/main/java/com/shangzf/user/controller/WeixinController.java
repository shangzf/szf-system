package com.shangzf.user.controller;

import com.shangzf.common.util.ConvertUtil;
import com.shangzf.user.api.dto.WeixinDTO;
import com.shangzf.user.entity.Weixin;
import com.shangzf.user.service.IWeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
/**
 * <p>
 * 微信绑定信息 前端控制器
 * </p>
 */
=======
>>>>>>> origin/dev
@RestController
@RequestMapping("/user/weixin")
public class WeixinController {

    @Autowired
    private IWeixinService weixinService;

    @GetMapping("/user/{userId}")
    public WeixinDTO getInfoByUserId(@PathVariable("userId") Long userId) {
        Weixin weixin = weixinService.getInfoByUserId(userId);
        return ConvertUtil.convert(weixin, WeixinDTO.class);
    }

    @GetMapping("/open/{openId}")
    public WeixinDTO getInfoByOpenId(@PathVariable("openId") String openId) {
        Weixin weixin = weixinService.getInfoByOpenId(openId);
        return ConvertUtil.convert(weixin, WeixinDTO.class);
    }

    @GetMapping("/union/{unionId}")
    public WeixinDTO getInfoByUnionId(@PathVariable("unionId") String unionId) {
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

    @PutMapping("/unbind/{userId}")
    public Boolean unbind(@PathVariable("userId") Long userId){
        return weixinService.unbind(userId);
    }
}
