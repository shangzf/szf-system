package com.shangzf.ad.remote;

import com.shangzf.ad.dto.PromotionSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "szf-ad-boot")
public interface AdRemoteService {

    @GetMapping("/space/all")
    List<PromotionSpaceDTO> getAll();

    @GetMapping("/space/keys")
    List<PromotionSpaceDTO> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys);

    @PostMapping("/space/saveOrUpdate")
    void saveOrUpdate(@RequestBody PromotionSpaceDTO dto);

    @GetMapping("/space/{id}")
    PromotionSpaceDTO getById(@PathVariable("id") Long id);
}
