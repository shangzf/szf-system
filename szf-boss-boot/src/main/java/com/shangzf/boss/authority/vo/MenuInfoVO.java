package com.shangzf.boss.authority.vo;

import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Schema(name = "菜单信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoVO implements Serializable {
    private static final long serialVersionUID = 6013519795805964936L;
    @Schema(name = "当前的菜单信息")
    private MenuDTO menuInfo;
    @Schema(name = "父级菜单列表")
    private List<MenuNodeDTO> parentMenus;
}
