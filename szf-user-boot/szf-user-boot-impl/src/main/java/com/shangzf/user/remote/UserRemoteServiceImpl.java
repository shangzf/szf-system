package com.shangzf.user.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.user.dto.PasswordDTO;
import com.shangzf.user.dto.UserDTO;
import com.shangzf.user.entity.User;
import com.shangzf.user.param.UserQueryParam;
import com.shangzf.user.service.IUserService;
import com.shangzf.user.util.PageUtils;
import com.shangzf.util.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("/page")
    public Page<UserDTO> getUserPage(@RequestBody UserQueryParam queryParam) {
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

    @Override
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return ConvertUtils.convert(user, UserDTO.class);
    }

    @Override
    @GetMapping("/phone/{phone}")
    public UserDTO getByPhone(@PathVariable("phone") String phone) {
        List<User> userList = userService.lambdaQuery().eq(User::getPhone, phone).orderByDesc(User::getId).list();
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return ConvertUtils.convert(userList.get(0), UserDTO.class);
    }

    @Override
    @GetMapping("/register/{phone}")
    public Boolean checkRegister(@PathVariable("phone") String phone) {
        UserDTO userDTO = getByPhone(phone);
        return Objects.nonNull(userDTO) && !userDTO.getDeleted();
    }

    @Override
    @PostMapping("/")
    public UserDTO save(@RequestBody UserDTO dto) {
        User user = ConvertUtils.convert(dto, User.class);
        Optional.ofNullable(user).ifPresent(u -> {

        });
        return dto;
    }

    @Override
    @PutMapping("/")
    public Boolean update(@RequestBody UserDTO dto) {
        return null;
    }

    @Override
    @GetMapping("/updatePwd/{id}")
    public Boolean checkUpdatePassword(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    @PutMapping("/pwd")
    public Boolean editPassword(@RequestBody PasswordDTO dto) {
        return null;
    }
}
