package com.shangzf.authority.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(name = "给用户分配角色")
@Data
@NoArgsConstructor
public class AllocateUserRolesDTO implements Serializable {

    private static final long serialVersionUID = -8084528415227985584L;
    private Long userId;
    private List<Long> userRoles;

}
