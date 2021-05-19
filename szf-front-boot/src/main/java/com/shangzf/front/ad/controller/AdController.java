package com.shangzf.front.ad.controller;

import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.AdRemoteService;
import com.shangzf.common.vo.response.QueryResponseData;
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
    private AdRemoteService adRemoteService;

    @GetMapping("/space/keys")
    public QueryResponseData<List<PromotionSpaceDTO>> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys){
        List<PromotionSpaceDTO> list = adRemoteService.getBySpaceKeys(spaceKeys);
        return QueryResponseData.success(list);
    }
}
