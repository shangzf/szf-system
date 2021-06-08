package com.shangzf.authority.remote;


import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.entity.Roles;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.common.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleRemoteService {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles")
    public List<RoleDTO> getRolesByUserId(@RequestParam("userId") Long userId) {
        List<Roles> roles = rolesService.getRolesByUserId(userId);
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @DeleteMapping("/")
    public boolean delete(@RequestParam("id") Long id) {
        return rolesService.deleteWithAssociation(id);
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@RequestParam("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<RoleDTO> getAll() {
        List<Roles> roles = rolesService.getAll();
        return ConvertUtil.convertList(roles, RoleDTO.class);
    }

    @PostMapping("/allocate")
    public boolean allocateUserRoles(@RequestBody AllocateUserRolesDTO dto) {
        return rolesService.allocateUserRoles(dto);
    }
}
