package com.shangzf.front.ad.controller;

import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.IAdRemoteService;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.front.ad.vo.PromotionAdVO;
import com.shangzf.front.ad.vo.PromotionSpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private IAdRemoteService adRemoteService;

    @GetMapping("/space/keys")
    public ResultResponse<List<PromotionSpaceVO>> getBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys){
        List<PromotionSpaceDTO> dtoList = adRemoteService.getBySpaceKeys(spaceKeys);
        List<PromotionSpaceVO> voList = dtoList.stream().map(promotionSpaceDTO -> {
            List<PromotionAdVO> adVOList = ConvertUtil
                    .convertList(promotionSpaceDTO.getAdDTOList(), PromotionAdVO.class);
            PromotionSpaceVO spaceVO = ConvertUtil.convert(promotionSpaceDTO, PromotionSpaceVO.class);
            if (Objects.nonNull(spaceVO)) {
                spaceVO.setAdVOList(adVOList);
            }
            return spaceVO;
        }).collect(Collectors.toList());
        return ResultResponse.successOfData(voList);
    }
}
