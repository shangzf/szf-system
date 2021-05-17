package com.shangzf.ad.controller;

import com.shangzf.ad.dto.PromotionAdDTO;
import com.shangzf.ad.dto.PromotionSpaceDTO;
import com.shangzf.ad.remote.AdRemoteService;
import com.shangzf.response.ResponseDTO;
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

    private final AdRemoteService adRemoteService;

    public AdController(final AdRemoteService adRemoteService) {
        this.adRemoteService = adRemoteService;
    }

    @GetMapping("/space/all")
    public ResponseDTO<List<PromotionSpaceDTO>> getAllSpace(){
        List<PromotionSpaceDTO> dtoList = adRemoteService.getAll();
        return ResponseDTO.success(dtoList);
    }

    @PostMapping("/space/saveOrUpdate")
    public ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto){
        return adRemoteService.saveOrUpdateSpace(dto);
    }

    @GetMapping("/space/{id}")
    public ResponseDTO<PromotionSpaceDTO> getSpaceById(@PathVariable("id") Long id){
        PromotionSpaceDTO dto = adRemoteService.getSpaceById(id);
        return ResponseDTO.success(dto);
    }

    @GetMapping("/all")
    public ResponseDTO<List<PromotionAdDTO>> getAllAds(){
        List<PromotionAdDTO> dtoList = adRemoteService.getAllAds();
        return ResponseDTO.success(dtoList);
    }

    @PostMapping("/saveOrUpdate")
    public ResponseDTO saveOrUpdateAd(@RequestBody PromotionAdDTO dto){
        return adRemoteService.saveOrUpdateAd(dto);
    }

    @GetMapping("/{id}")
    public ResponseDTO<PromotionAdDTO> getAdById(@PathVariable("id") Long id){
        PromotionAdDTO dto = adRemoteService.getAdById(id);
        return ResponseDTO.success(dto);
    }
}
