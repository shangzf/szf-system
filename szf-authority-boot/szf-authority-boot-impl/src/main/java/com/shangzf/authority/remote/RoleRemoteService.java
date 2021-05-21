package com.shangzf.authority.remote;


import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.common.vo.response.ResultResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleRemoteService {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles/{userId}")
    public ResultResponseData<List<RoleDTO>> getRolesByUserId(@PathVariable("userId") Long userId) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResultResponseData<Boolean> delete(@PathVariable("id") Long id) {
        return ResultResponseData.success(rolesService.removeById(id));
    }

    @GetMapping("/{id}")
    public ResultResponseData<RoleDTO> getById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public ResultResponseData<List<RoleDTO>> getAll() {
        return null;
    }
}
