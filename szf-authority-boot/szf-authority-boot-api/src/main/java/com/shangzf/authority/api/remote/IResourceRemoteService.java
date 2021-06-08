package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "szf-authority-boot",path = "/resource")
public interface IResourceRemoteService {

    @DeleteMapping("/")
    boolean delete(@RequestParam("id") Long id);

    @DeleteMapping("/category")
    boolean deleteCategory(@RequestParam("id") Long id);

    @PostMapping("/allocate")
    boolean allocateRoleResources(@RequestBody AllocateRoleResourceDTO dto);

    @GetMapping("/resources")
    List<ResourceDTO> getResourcesByRoleId(@RequestParam("roleId") Long roleId);
}
