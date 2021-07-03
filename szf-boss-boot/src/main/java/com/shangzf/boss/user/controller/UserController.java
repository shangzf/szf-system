package com.shangzf.boss.user.controller;

import com.shangzf.boss.user.vo.UserVO;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.ResultResponse;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.dto.param.UserParam;
import com.shangzf.user.api.remote.IUserRemoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRemoteService userRemoteService;

    @Operation(summary = "用户分页数据", description = "根据条件获取角色分页数据")
    @GetMapping("/page")
    public ResultResponse<DataGrid<UserVO>> getRolesByPage(UserParam param) {
        DataGrid<UserDTO> dataGrid = userRemoteService.getUserPage(param);
        List<UserVO> voList = ConvertUtil.convertList(dataGrid.getRecords(), UserVO.class);
        return ResultResponse.successOfData(new DataGrid<>(voList, dataGrid));
    }
}
