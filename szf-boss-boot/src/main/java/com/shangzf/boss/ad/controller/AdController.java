package com.shangzf.boss.ad.controller;

import com.shangzf.ad.api.dto.PromotionAdDTO;
import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.IAdRemoteService;
import com.shangzf.common.vo.response.ResultResponseData;
import com.shangzf.common.vo.response.ResponseData;
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
    public ResultResponseData<List<PromotionSpaceDTO>> getAllSpace(){
        List<PromotionSpaceDTO> dtoList = adRemoteService.getAll();
        return ResultResponseData.success(dtoList);
    }

    @PostMapping("/space/saveOrUpdate")
    public ResponseData saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto){
        return adRemoteService.saveOrUpdateSpace(dto);
    }

    @GetMapping("/space/{id}")
    public ResultResponseData<PromotionSpaceDTO> getSpaceById(@PathVariable("id") Long id){
        PromotionSpaceDTO dto = adRemoteService.getSpaceById(id);
        return ResultResponseData.success(dto);
    }

    @GetMapping("/all")
    public ResultResponseData<List<PromotionAdDTO>> getAllAds(){
        List<PromotionAdDTO> dtoList = adRemoteService.getAllAds();
        return ResultResponseData.success(dtoList);
    }

    @PostMapping("/saveOrUpdate")
    public ResponseData saveOrUpdateAd(@RequestBody PromotionAdDTO dto){
        return adRemoteService.saveOrUpdateAd(dto);
    }

    @GetMapping("/{id}")
    public ResultResponseData<PromotionAdDTO> getAdById(@PathVariable("id") Long id){
        PromotionAdDTO dto = adRemoteService.getAdById(id);
        return ResultResponseData.success(dto);
    }
}
