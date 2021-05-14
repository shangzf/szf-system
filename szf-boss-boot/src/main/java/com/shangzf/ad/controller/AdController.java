package com.shangzf.ad.controller;

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
    public ResponseDTO<PromotionSpaceDTO> saveOrUpdate(@RequestBody PromotionSpaceDTO dto){
        adRemoteService.saveOrUpdate(dto);
        return ResponseDTO.success();
    }

    @GetMapping("/space/{id}")
    public ResponseDTO<PromotionSpaceDTO> getById(@PathVariable("id") Long id){
        PromotionSpaceDTO dto = adRemoteService.getById(id);
        return ResponseDTO.success(dto);
    }
}
