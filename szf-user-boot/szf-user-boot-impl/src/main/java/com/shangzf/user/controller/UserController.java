package com.shangzf.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shangzf.common.util.ConvertUtil;
import com.shangzf.common.web.pojo.vo.page.DataGrid;
import com.shangzf.user.api.dto.ChangePasswordDTO;
import com.shangzf.user.api.dto.PasswordDTO;
import com.shangzf.user.api.dto.UserDTO;
import com.shangzf.user.api.dto.param.UserParam;
import com.shangzf.user.entity.User;
import com.shangzf.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 */
=======
import java.util.List;
import java.util.Objects;

>>>>>>> origin/dev
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/page")
    public DataGrid<UserDTO> getUserPage(UserParam param) {
        Page<User> userPage = userService.getUsersByPage(param);
        List<UserDTO> dtoList = ConvertUtil.convertList(userPage.getRecords(), UserDTO.class);
        return new DataGrid<>(dtoList, userPage.getTotal(), userPage.getSize(), userPage.getCurrent());
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    @GetMapping("/phone/{phone}")
    public UserDTO getByPhone(@PathVariable("phone") String phone) {
        User user = userService.getUserByPhone(phone);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    @GetMapping("/register/{phone}")
    public Boolean checkRegister(@PathVariable("phone") String phone) {
        UserDTO userDTO = getByPhone(phone);
        return Objects.nonNull(userDTO);
    }

    @PostMapping("/")
    public Boolean save(@RequestBody UserDTO dto) {
        User user = ConvertUtil.convert(dto, User.class);
        if (Objects.isNull(user)) {
            return Boolean.FALSE;
        }
        return userService.saveUser(user);
    }

    @PutMapping("/")
    public Boolean update(@RequestBody UserDTO dto) {
        User user = ConvertUtil.convert(dto, User.class);
        if (Objects.isNull(user)) {
            return Boolean.FALSE;
        }
        return userService.updateUser(user);
    }

    @GetMapping("/pwd/check/{id}")
    public Boolean checkPassword(@PathVariable("id") Long id) {
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
