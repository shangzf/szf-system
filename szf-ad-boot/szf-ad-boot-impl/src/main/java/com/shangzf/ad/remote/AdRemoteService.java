package com.shangzf.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangzf.ad.api.dto.PromotionAdDTO;
import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.enums.StatusEnum;
import com.shangzf.ad.entity.PromotionAd;
import com.shangzf.ad.entity.PromotionSpace;
import com.shangzf.ad.service.IPromotionAdService;
import com.shangzf.ad.service.IPromotionSpaceService;
import com.shangzf.common.util.ConvertUtil;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/ad")
public class AdRemoteService {

    @Autowired
    private IPromotionSpaceService promotionSpaceService;
    @Autowired
    private IPromotionAdService promotionAdService;

    @GetMapping("/space/all")
    public List<PromotionSpaceDTO> getAll() {
        List<PromotionSpace> spaceList = promotionSpaceService.list();
        return ConvertUtil.convertList(spaceList, PromotionSpaceDTO.class);
    }

    @GetMapping("/space/keys")
    public List<PromotionSpaceDTO> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys) {
        if (Arrays.isNullOrContainsNull(spaceKeys)) {
            return Collections.emptyList();
        }
        List<PromotionSpaceDTO> spaceDTOList = new ArrayList<>(spaceKeys.length);
        for (String spaceKey : spaceKeys) {
            QueryWrapper<PromotionSpace> spaceQueryWrapper = new QueryWrapper<>();
            spaceQueryWrapper.eq("space_key", spaceKey);
            spaceQueryWrapper.eq("deleted", Boolean.FALSE);
            PromotionSpace promotionSpace = promotionSpaceService.getOne(spaceQueryWrapper);
            PromotionSpaceDTO spaceDTO = ConvertUtil.convert(promotionSpace, PromotionSpaceDTO.class);
            Optional.ofNullable(spaceDTO).ifPresent(dto -> {
                QueryWrapper<PromotionAd> adQueryWrapper = new QueryWrapper<>();
                adQueryWrapper.eq("space_id", promotionSpace.getId());
                // 状态
                adQueryWrapper.eq("status", StatusEnum.UP);
                // 有效期
                Date now = new Date();
                adQueryWrapper.lt("start_time", now);
                adQueryWrapper.gt("end_time", now);
                List<PromotionAd> adList = promotionAdService.list(adQueryWrapper);
                List<PromotionAdDTO> adDTOList = ConvertUtil.convertList(adList, PromotionAdDTO.class);
                Optional.ofNullable(adDTOList).ifPresent(dto::setAdDTOList);
                spaceDTOList.add(dto);
            });
        }
        return spaceDTOList;
    }

    @PostMapping("/space/saveOrUpdate")
    public Boolean saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto) {
        PromotionSpace promotionSpace = ConvertUtil.convert(dto, PromotionSpace.class);
        if (Objects.isNull(promotionSpace)) {
            return Boolean.FALSE;
        }
        promotionSpaceService.saveOrUpdate(promotionSpace);
        return Boolean.TRUE;
    }

    @PostMapping("/space")
    public PromotionSpaceDTO getSpaceById(@RequestParam("id") Long id) {
        PromotionSpace space = promotionSpaceService.getById(id);
        return ConvertUtil.convert(space, PromotionSpaceDTO.class);
    }

    @GetMapping("/all")
    public List<PromotionAdDTO> getAllAds() {
        List<PromotionAd> adList = promotionAdService.list();
        return ConvertUtil.convertList(adList, PromotionAdDTO.class);
    }

    @PostMapping("/saveOrUpdate")
    public Boolean saveOrUpdateAd(@RequestBody PromotionAdDTO dto) {
        PromotionAd promotionAd = ConvertUtil.convert(dto, PromotionAd.class);
        if (Objects.isNull(promotionAd)) {
            return Boolean.FALSE;
        }
        promotionAdService.saveOrUpdate(promotionAd);
        return Boolean.TRUE;
    }

    @GetMapping("/")
    public PromotionAdDTO getAdById(@RequestParam("id") Long id) {
        PromotionAd promotionAd = promotionAdService.getById(id);
        return ConvertUtil.convert(promotionAd, PromotionAdDTO.class);
    }
}
