package com.shangzf.authority.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllocateRoleMenusDTO implements Serializable {
    private static final long serialVersionUID = 484705641526230227L;

    private Long roleId;
    private List<Long> roleMenus;

}
