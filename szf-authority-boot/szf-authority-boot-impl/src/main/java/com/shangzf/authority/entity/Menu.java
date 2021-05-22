package com.shangzf.authority.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 菜单
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Menu extends AuthorityBase {

    private static final long serialVersionUID = -1037094217080977988L;
    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 菜单地址
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;

    /**
     * 是否显示
     */
    private Boolean shown;

    /**
     * 序号
     */
    private Integer orderNum;

    /**
     * 等级
     */
    private Integer level;


}
