package com.shangzf.authority.remote;


import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.common.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleRemoteService {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles/{userId}")
    public List<RoleDTO> getRolesByUserId(@PathVariable("userId") Long userId) {
        List<Roles> roles = rolesService.getRolesByUserId(userId);
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return rolesService.deleteWithAssociation(id);
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<RoleDTO> getAll() {
        List<Roles> roles = rolesService.getAll();
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @PostMapping("/allocateUserRoles")
    public boolean allocateUserRoles(@RequestBody AllocateUserRolesDTO dto) {
        return rolesService.allocateUserRoles(dto);
    }
}
