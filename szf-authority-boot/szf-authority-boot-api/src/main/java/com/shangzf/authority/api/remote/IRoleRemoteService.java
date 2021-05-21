package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.RoleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "szf-authority-boot", path = "/role")
public interface IRoleRemoteService {

    @GetMapping("/roles/{userId}")
    List<RoleDTO> getRolesByUserId(@PathVariable("userId") Long userId);

    @DeleteMapping("/{id}")
    boolean delete(@PathVariable("id")Long id);

    @GetMapping("/{id}")
    RoleDTO getById(@PathVariable("id")Long id);

    @GetMapping("/all")
    List<RoleDTO> getAll();
}
