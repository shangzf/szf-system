package com.shangzf.ad.api.remote;

import com.shangzf.ad.api.dto.PromotionAdDTO;
import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "szf-ad-boot",path = "/ad")
public interface IAdRemoteService {

    @GetMapping("/space/all")
    List<PromotionSpaceDTO> getAll();

    @GetMapping("/space/keys")
    List<PromotionSpaceDTO> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys);

    @PostMapping("/space/saveOrUpdate")
    Boolean saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto);

    @GetMapping("/space")
    PromotionSpaceDTO getSpaceById(@RequestParam("id") Long id);

    @GetMapping("/all")
    List<PromotionAdDTO> getAllAds();

    @PostMapping("/saveOrUpdate")
    Boolean saveOrUpdateAd(@RequestBody PromotionAdDTO dto);

    @GetMapping("/")
    PromotionAdDTO getAdById(@RequestParam("id") Long id);
}
