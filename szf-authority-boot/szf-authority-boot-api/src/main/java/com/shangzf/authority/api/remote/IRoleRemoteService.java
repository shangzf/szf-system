package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.AllocateUserRolesDTO;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.dto.param.RoleParam;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "szf-authority-boot", path = "/role")
public interface IRoleRemoteService {

    @GetMapping("/roles/{userId}")
    List<RoleDTO> getRolesByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/")
    Boolean save(@RequestBody RoleDTO dto);

    @PutMapping("/")
    Boolean update(@RequestBody RoleDTO dto);

    @DeleteMapping("/{id}")
    boolean delete(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    RoleDTO getById(@PathVariable("id") Long id);

    @GetMapping("/all")
    List<RoleDTO> getAll();

    @PostMapping("/allocate")
    boolean allocateUserRoles(@RequestBody AllocateUserRolesDTO dto);

    @GetMapping("/page")
    DataGrid<RoleDTO> getRolesByPage(@SpringQueryMap RoleParam param);
}
