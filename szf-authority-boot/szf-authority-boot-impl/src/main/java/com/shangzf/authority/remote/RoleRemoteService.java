package com.shangzf.authority.remote;


import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.authority.service.IRolesService;
import com.shangzf.common.vo.response.ResultResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleRemoteService implements IRoleRemoteService {

    @Autowired
    private IRolesService rolesService;

    @Override
    @GetMapping("/roles/{userId}")
    public ResultResponseData<Set<RoleDTO>> getRolesByUserId(@PathVariable("userId") Long userId) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResultResponseData<Boolean> delete(@PathVariable("id") Long id) {
        return ResultResponseData.success(rolesService.removeById(id));
    }

    @Override
    @GetMapping("/{id}")
    public ResultResponseData<RoleDTO> getById(@PathVariable("id") Long id) {
        return null;
    }
}
