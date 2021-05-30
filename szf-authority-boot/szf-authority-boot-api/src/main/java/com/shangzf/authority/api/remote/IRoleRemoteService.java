package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "szf-authority-boot", path = "/role")
public interface IRoleRemoteService {

    @GetMapping("/roles")
    List<RoleDTO> getRolesByUserId(@RequestParam("userId") Long userId);

    @DeleteMapping("/")
    boolean delete(@RequestParam("id") Long id);

    @GetMapping("/")
    RoleDTO getById(@RequestParam("id") Long id);

    @GetMapping("/all")
    List<RoleDTO> getAll();

    @PostMapping("/allocate")
    boolean allocateUserRoles(@RequestBody AllocateUserRolesDTO dto);
}
