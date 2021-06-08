package com.shangzf.user.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.user.api.dto.ChangePasswordDTO;
import com.shangzf.user.api.dto.PasswordDTO;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.param.UserQueryParam;
import com.shangzf.user.entity.User;
import com.shangzf.user.service.IUserService;
import com.shangzf.user.util.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRemoteService {

    @Autowired
    private IUserService userService;

    @GetMapping("/page")
    public Page<UserDTO> getUserPage(UserQueryParam queryParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Optional.ofNullable(queryParam.getPhone()).ifPresent(phone -> queryWrapper.like("phone", phone));
        Optional.ofNullable(queryParam.getUserId()).ifPresent(id -> queryWrapper.eq("id", id));
        if (Objects.nonNull(queryParam.getStartCreateTime()) && Objects.nonNull(queryParam.getEndCreateTime())) {
            queryWrapper.ge("create_time", queryParam.getStartCreateTime());
            queryWrapper.le("create_time", queryParam.getEndCreateTime());
        }
        queryWrapper.orderByDesc("id");
        Page<User> userPage = userService
                .page(new Page<>(queryParam.getCurrentPage(), queryParam.getPageSize()), queryWrapper);
        return PageUtils.page(userPage, UserDTO.class);
    }

    @GetMapping("/")
    public UserDTO getById(@RequestParam("id") Long id) {
        User user = userService.getById(id);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    @GetMapping("/phone")
    public UserDTO getByPhone(@RequestParam("phone") String phone) {
        List<User> userList = userService.lambdaQuery().eq(User::getPhone, phone).orderByDesc(User::getId).list();
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return ConvertUtil.convert(userList.get(0), UserDTO.class);
    }

    @GetMapping("/register")
    public Boolean checkRegister(@RequestParam("phone") String phone) {
        UserDTO userDTO = getByPhone(phone);
        return Objects.nonNull(userDTO) && !userDTO.getDeleted();
    }

    @PostMapping("/")
    public Boolean save(@RequestBody UserDTO dto) {
        User user = ConvertUtil.convert(dto, User.class);
        Optional.ofNullable(user).ifPresent(u -> {

        });
        return null;
    }

    @PutMapping("/")
    public Boolean update(@RequestBody UserDTO dto) {
        return null;
    }

    @GetMapping("/pwd/check")
    public Boolean checkPassword(@RequestParam("id") Long id) {
        return null;
    }

    /**
     * 修改密码
     *
     * @param dto 密码
     * @return 是否操作成功
     */
    @PutMapping("/pwd/update")
    public Boolean updatePassword(@RequestBody ChangePasswordDTO dto) {
        return null;
    }

    /**
     * 设置密码
     *
     * @param dto 密码
     * @return 是否操作成功
     */
    @PutMapping("/pwd/save")
    public Boolean savePassword(@RequestBody PasswordDTO dto) {
        return null;
    }
}
