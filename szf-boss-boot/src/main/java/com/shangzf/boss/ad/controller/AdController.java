package com.shangzf.boss.ad.controller;

import com.shangzf.ad.api.dto.PromotionAdDTO;
import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.IAdRemoteService;
import com.shangzf.common.vo.response.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final IAdRemoteService adRemoteService;

    public AdController(final IAdRemoteService adRemoteService) {
        this.adRemoteService = adRemoteService;
    }

    @GetMapping("/space/all")
    public ResultResponse<List<PromotionSpaceDTO>> getAllSpace() {
        List<PromotionSpaceDTO> dtoList = adRemoteService.getAll();
        return ResultResponse.success(dtoList);
    }

    @PostMapping("/space/saveOrUpdate")
    public ResultResponse<?> saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto) {
        Boolean result = adRemoteService.saveOrUpdateSpace(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @GetMapping("/space/{id}")
    public ResultResponse<PromotionSpaceDTO> getSpaceById(@PathVariable("id") Long id) {
        PromotionSpaceDTO dto = adRemoteService.getSpaceById(id);
        return ResultResponse.success(dto);
    }

    @GetMapping("/all")
    public ResultResponse<List<PromotionAdDTO>> getAllAds() {
        List<PromotionAdDTO> dtoList = adRemoteService.getAllAds();
        return ResultResponse.success(dtoList);
    }

    @PostMapping("/saveOrUpdate")
    public ResultResponse<?> saveOrUpdateAd(@RequestBody PromotionAdDTO dto) {
        Boolean result = adRemoteService.saveOrUpdateAd(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @GetMapping("/{id}")
    public ResultResponse<PromotionAdDTO> getAdById(@PathVariable("id") Long id) {
        PromotionAdDTO dto = adRemoteService.getAdById(id);
        return ResultResponse.success(dto);
    }
}
