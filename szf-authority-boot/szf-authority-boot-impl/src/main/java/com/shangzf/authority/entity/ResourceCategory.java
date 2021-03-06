package com.shangzf.authority.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 资源类别
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceCategory extends AuthorityBase {

    private static final long serialVersionUID = -8272542315192676054L;
    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别排序
     */
    private Integer sort;

}
