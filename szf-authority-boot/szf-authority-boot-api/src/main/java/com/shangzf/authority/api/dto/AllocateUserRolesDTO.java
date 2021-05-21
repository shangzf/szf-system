package com.shangzf.authority.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllocateUserRolesDTO{

    private static final long serialVersionUID = -8084528415227985584L;
    private Long userId;
    private List<Long> userRoles;

}
