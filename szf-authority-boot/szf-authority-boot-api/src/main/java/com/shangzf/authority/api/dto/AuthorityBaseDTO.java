package com.shangzf.authority.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class AuthorityBaseDTO implements Serializable {

    private static final long serialVersionUID = 5305252239737887218L;

    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lasModifyTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 最后修改者
     */
    private String lasModifyBy;
}
