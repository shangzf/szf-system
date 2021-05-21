package com.shangzf.boss.authority.controller;

import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.common.vo.response.ResultResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleRemoteService roleRemoteService;

    @DeleteMapping("/{id}")
    public ResultResponseData<Boolean> delete(@PathVariable("id") Long id){
        return roleRemoteService.delete(id);
    }

    @GetMapping("/{id}")
    public ResultResponseData<RoleDTO> getById(@PathVariable("id") Long id){
        return roleRemoteService.getById(id);
    }
}
