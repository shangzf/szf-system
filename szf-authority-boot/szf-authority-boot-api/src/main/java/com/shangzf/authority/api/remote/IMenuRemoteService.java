package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AllocateRoleMenusDTO;
import com.shangzf.authority.api.dto.MenuDTO;
import com.shangzf.authority.api.dto.MenuNodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "szf-authority-boot", path = "/menu")
public interface IMenuRemoteService {

    @DeleteMapping("/{id}")
    boolean delete(@PathVariable("id") Long id);

    @GetMapping("/node")
    List<MenuNodeDTO> getMenuNodeList();

    @GetMapping("/{id}")
    MenuDTO getById(@PathVariable("id") Long id);

    @GetMapping("/roles/{roleId}")
    List<MenuDTO> getMenusByRoleId(@PathVariable("roleId") Long roleId);

    @PostMapping("/allocate")
    boolean allocateRoleMenus(@RequestBody AllocateRoleMenusDTO dto);
}
