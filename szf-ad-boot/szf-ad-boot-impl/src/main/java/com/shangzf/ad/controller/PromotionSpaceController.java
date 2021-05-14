package com.shangzf.ad.controller;


import com.shangzf.ad.entity.PromotionSpace;
import com.shangzf.ad.service.IPromotionSpaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 广告位 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/space")
public class PromotionSpaceController {

    private final IPromotionSpaceService promotionSpaceService;

    public PromotionSpaceController(final IPromotionSpaceService promotionSpaceService) {
        this.promotionSpaceService = promotionSpaceService;
    }

    @GetMapping("/all")
    public List<PromotionSpace> getAll(){
        return promotionSpaceService.list();
    }
}
