package com.shangzf.boss.authority.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(description = "菜单信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoVO implements Serializable {
    private static final long serialVersionUID = 6013519795805964936L;

    @Schema(description = "当前的菜单信息")
    private MenuVO menuInfo;

    @Schema(description = "父级菜单列表")
    private List<MenuNodeVO> parentMenus;
}
