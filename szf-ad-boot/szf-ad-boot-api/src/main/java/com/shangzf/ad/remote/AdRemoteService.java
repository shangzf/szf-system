package com.shangzf.ad.remote;

import com.shangzf.ad.dto.PromotionAdDTO;
import com.shangzf.ad.dto.PromotionSpaceDTO;
import com.shangzf.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "szf-ad-boot",path = "/ad")
public interface AdRemoteService {

    @GetMapping("/space/all")
    List<PromotionSpaceDTO> getAll();

    @GetMapping("/space/keys")
    List<PromotionSpaceDTO> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys);

    @PostMapping("/space/saveOrUpdate")
    ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto);

    @GetMapping("/space/{id}")
    PromotionSpaceDTO getSpaceById(@PathVariable("id") Long id);

    @GetMapping("/all")
    List<PromotionAdDTO> getAllAds();

    @PostMapping("/saveOrUpdate")
    ResponseDTO saveOrUpdateAd(@RequestBody PromotionAdDTO dto);

    @GetMapping("/{id}")
    PromotionAdDTO getAdById(@PathVariable("id") Long id);
}
