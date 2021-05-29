package com.shangzf.boss.ad.controller;

import com.shangzf.ad.api.dto.PromotionAdDTO;
import com.shangzf.ad.api.dto.PromotionSpaceDTO;
import com.shangzf.ad.api.remote.IAdRemoteService;
import com.shangzf.boss.ad.vo.PromotionAdVO;
import com.shangzf.boss.ad.vo.PromotionSpaceVO;
import com.shangzf.common.pojo.vo.ResultResponse;
import com.shangzf.common.util.ConvertUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ad")
public class AdController {

    private final IAdRemoteService adRemoteService;

    public AdController(final IAdRemoteService adRemoteService) {
        this.adRemoteService = adRemoteService;
    }

    @GetMapping("/space/all")
    public ResultResponse<List<PromotionSpaceVO>> getAllSpace() {
        List<PromotionSpaceDTO> dtoList = adRemoteService.getAll();
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

    @PostMapping("/space/saveOrUpdate")
    public ResultResponse<?> saveOrUpdateSpace(@RequestBody PromotionSpaceDTO dto) {
        Boolean result = adRemoteService.saveOrUpdateSpace(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @GetMapping("/space/{id}")
    public ResultResponse<PromotionSpaceVO> getSpaceById(@PathVariable("id") Long id) {
        PromotionSpaceDTO dto = adRemoteService.getSpaceById(id);
        PromotionSpaceVO spaceVO = ConvertUtil.convert(dto, PromotionSpaceVO.class);
        return ResultResponse.successOfData(spaceVO);
    }

    @GetMapping("/all")
    public ResultResponse<List<PromotionAdVO>> getAllAds() {
        List<PromotionAdDTO> dtoList = adRemoteService.getAllAds();
        List<PromotionAdVO> voList = dtoList.stream().map(dto -> ConvertUtil.convert(dto, PromotionAdVO.class))
                                            .collect(Collectors.toList());
        return ResultResponse.successOfData(voList);
    }

    @PostMapping("/saveOrUpdate")
    public ResultResponse<?> saveOrUpdateAd(@RequestBody PromotionAdDTO dto) {
        Boolean result = adRemoteService.saveOrUpdateAd(dto);
        return result ? ResultResponse.success() : ResultResponse.fail();
    }

    @GetMapping("/{id}")
    public ResultResponse<PromotionAdVO> getAdById(@PathVariable("id") Long id) {
        PromotionAdDTO dto = adRemoteService.getAdById(id);
        PromotionAdVO adVO = ConvertUtil.convert(dto, PromotionAdVO.class);
        return ResultResponse.successOfData(adVO);
    }
}
