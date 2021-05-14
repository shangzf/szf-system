package com.shangzf.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangzf.ad.dto.PromotionAdDTO;
import com.shangzf.ad.dto.PromotionSpaceDTO;
import com.shangzf.ad.entity.PromotionAd;
import com.shangzf.ad.entity.PromotionSpace;
import com.shangzf.ad.service.IPromotionAdService;
import com.shangzf.ad.service.IPromotionSpaceService;
import com.shangzf.common.DeleteEnum;
import com.shangzf.common.StatusEnum;
import com.shangzf.util.ConvertUtils;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AdRemoteServiceImpl implements AdRemoteService {

    @Autowired
    private IPromotionSpaceService promotionSpaceService;
    @Autowired
    private IPromotionAdService promotionAdService;

    @Override
    @GetMapping("/space/all")
    public List<PromotionSpaceDTO> getAll() {
        List<PromotionSpace> spaceList = promotionSpaceService.list();
        return ConvertUtils.convertList(spaceList, PromotionSpaceDTO.class);
    }

    @Override
    @GetMapping("/space/keys")
    public List<PromotionSpaceDTO> getBySpaceKeys(String[] spaceKeys) {
        if (Arrays.isNullOrContainsNull(spaceKeys)) {
            return Collections.emptyList();
        }
        List<PromotionSpaceDTO> spaceDTOList = new ArrayList<>(spaceKeys.length);
        for (String spaceKey : spaceKeys) {
            QueryWrapper<PromotionSpace> spaceQueryWrapper = new QueryWrapper<>();
            spaceQueryWrapper.eq("space_key", spaceKey);
            spaceQueryWrapper.eq("deleted", DeleteEnum.NO.getCode());
            PromotionSpace promotionSpace = promotionSpaceService.getOne(spaceQueryWrapper);
            PromotionSpaceDTO spaceDTO = ConvertUtils.convert(promotionSpace, PromotionSpaceDTO.class);
            Optional.ofNullable(spaceDTO).ifPresent(dto -> {
                QueryWrapper<PromotionAd> adQueryWrapper = new QueryWrapper<>();
                adQueryWrapper.eq("space_id", promotionSpace.getId());
                // 状态
                adQueryWrapper.eq("status", StatusEnum.UP.getCode());
                // 有效期
                Date now = new Date();
                adQueryWrapper.lt("start_time", now);
                adQueryWrapper.gt("end_time", now);
                List<PromotionAd> adList = promotionAdService.list(adQueryWrapper);
                List<PromotionAdDTO> adDTOList = ConvertUtils.convertList(adList, PromotionAdDTO.class);
                Optional.ofNullable(adDTOList).ifPresent(dto::setAdDTOList);
                spaceDTOList.add(dto);
            });
        }
        return spaceDTOList;
    }

    @Override
    @PostMapping("/space/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PromotionSpaceDTO dto) {
        PromotionSpace space = ConvertUtils.convert(dto, PromotionSpace.class);
        promotionSpaceService.saveOrUpdate(space);
    }

    @Override
    @PostMapping("/space/{id}")
    public PromotionSpaceDTO getById(@PathVariable("id") Long id) {
        PromotionSpace space = promotionSpaceService.getById(id);
        return ConvertUtils.convert(space, PromotionSpaceDTO.class);
    }
}
