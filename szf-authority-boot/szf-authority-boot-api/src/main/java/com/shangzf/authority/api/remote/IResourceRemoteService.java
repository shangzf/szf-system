package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AllocateRoleResourceDTO;
import com.shangzf.authority.api.dto.ResourceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "szf-authority-boot",path = "/resource")
public interface IResourceRemoteService {

    @DeleteMapping("/{id}")
    boolean delete(@PathVariable("id") Long id);

    @DeleteMapping("/category/{id}")
    boolean deleteCategory(@PathVariable("id") Long id);

    @PostMapping("/allocate")
    boolean allocateRoleResources(@RequestBody AllocateRoleResourceDTO dto);

    @GetMapping("/resources/{roleId}")
    List<ResourceDTO> getResourcesByRoleId(@PathVariable("roleId") Long roleId);
}
