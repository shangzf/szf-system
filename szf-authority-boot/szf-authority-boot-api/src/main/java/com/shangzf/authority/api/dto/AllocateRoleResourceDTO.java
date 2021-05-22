package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(name = "给角色分配资源")
@Data
@NoArgsConstructor
public class AllocateRoleResourceDTO implements Serializable {

    private static final long serialVersionUID = 5390453807681931626L;
    private Long roleId;
    private List<Long> roleResources;
}