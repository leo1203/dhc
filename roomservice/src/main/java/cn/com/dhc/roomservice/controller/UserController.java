package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.User;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.service.UserService;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * 根据名字查找
     */
    @PostMapping(value = "/findByUserName")
    public ResultBean findByUserName(@RequestParam("userName") String userName) {
        if (null == userService.findByName(userName)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return ResultUtils.success(userService.findByName(userName));
    }

    /*
     * 根据ID查找
     */
    @PostMapping(value = "/findByUserId")
    public ResultBean findByUserId(@RequestParam("userId") String userId) {
        if (null == userService.findById(userId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return ResultUtils.success(userService.findById(userId));
    }

    /*
      插入记录数
    */
    @PostMapping(value = "/insertUser")
    public ResultBean insertUser(User user) {
        return ResultUtils.success(userService.insertUser(user));
    }

    /*
       删除用户信息记录
     */
    @PostMapping(value = "/deleteUser")
    public ResultBean deleteUser(@RequestParam("userId") String userId) {
        if (null == userService.findById(userId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return ResultUtils.success(userService.deleteUser(userId));
    }

    /*
       修改用户信息记录
     */
    @PostMapping(value = "/updateUser")
    public ResultBean updateUser(User user) {
        return ResultUtils.success(userService.updateUser(user));
    }

    /*
        逻辑delflag删除
     */
    @PostMapping(value = "/deleteUserDelFlag")
    public ResultBean deleteUserDelFlag(String userId) {
        if (null == userService.findById(userId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return ResultUtils.success(userService.deleteUserDelFlag(userId));
    }

    @GetMapping("/listUsers")
    public ResultBean listUsers() {
        return ResultUtils.success(userService.findAllUsers());
    }
}
