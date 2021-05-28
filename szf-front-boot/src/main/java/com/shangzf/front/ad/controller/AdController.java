package com.shangzf.front.ad.controller;

import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.IAdRemoteService;
import com.shangzf.common.vo.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private IAdRemoteService adRemoteService;

    @GetMapping("/space/keys")
    public ResultResponse<List<PromotionSpaceDTO>> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys){
        List<PromotionSpaceDTO> list = adRemoteService.getBySpaceKeys(spaceKeys);
        return ResultResponse.success(list);
    }
}
