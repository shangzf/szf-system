package com.shangzf.authority.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthorityBase implements Serializable {

    private static final long serialVersionUID = -8675023135565058768L;

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
    private Date lasModifyTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 最后修改者
     */
    @TableField(fill = FieldFill.UPDATE)
    private String lasModifyBy;
}
