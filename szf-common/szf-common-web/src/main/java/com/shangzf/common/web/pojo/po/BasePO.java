package com.shangzf.common.web.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePO implements Serializable {

    private static final long serialVersionUID = -3990982883645564601L;

    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date lastModifyTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 最后修改者
     */
    @TableField(fill = FieldFill.UPDATE)
    private String lastModifyBy;
}
