package com.shangzf.authority.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 资源
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Resource extends AuthorityBase {

    private static final long serialVersionUID = -4472136678167441478L;
    /**
     * 名称
     */
    private String name;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 资源类别
     */
    private Long categoryId;

    /**
     * 描述
     */
    private String remark;

}
