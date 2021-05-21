package com.shangzf.authority.api.remote;

import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.common.vo.response.ResultResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "szf-authority-boot", path = "/role")
public interface IRoleRemoteService {


    @GetMapping("/roles/{userId}")
    ResultResponseData<Set<RoleDTO>> getRolesByUserId(@PathVariable("userId") Long userId);

    @DeleteMapping("/{id}")
    ResultResponseData<Boolean> delete(@PathVariable("id")Long id);

    @GetMapping("/{id}")
    ResultResponseData<RoleDTO> getById(@PathVariable("id")Long id);
}
