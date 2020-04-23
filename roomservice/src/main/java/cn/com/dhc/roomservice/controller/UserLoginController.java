package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.MeetingRole;
import cn.com.dhc.roomservice.bean.User;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.service.MeetingRoleService;
import cn.com.dhc.roomservice.service.UserService;
import cn.com.dhc.roomservice.utils.JwtUtil;
import cn.com.dhc.roomservice.utils.MD5Utils;
import cn.com.dhc.roomservice.utils.ResultUtils;
import cn.com.dhc.roomservice.utils.VerifyUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @author huleikai
 * @create 2019-05-07 10:53
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private MeetingRoleService roleService;

    /**
     * 处理用户登录(注:待使用token完善，先做保留)
     *
     * @param mobile   手机号
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public ResultBean login(@RequestParam("mobile") String mobile,
                            @RequestParam("password") String password) {
        //校验
        isVerifiedUserLoginInfo(mobile, password);
        User user = userService.findByMobile(mobile);
        if (!user.getPassword().equals(MD5Utils.string2MD5(password))) {
            throw new BusinessException(Constants.VALUES.get(Constants.PASSWORD_IS_NOT_MEET_RULE));
        }
        MeetingRole meetingRole = roleService.findMeetingRoleByRoleId(user.getRoleId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        jsonObject.put("role", meetingRole);
        String token = JwtUtil.sign(mobile, MD5Utils.string2MD5(password));
        jsonObject.put("token", token);
        return ResultUtils.success(jsonObject);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/registry")
    public ResultBean registry(@Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        //校验
        isVerifiedUserLoginInfo(user.getMobile(), user.getPassword());
        try {
            userService.findByMobile(user.getMobile());
        } catch (BusinessException e) {
            return ResultUtils.success(userService.insertUser(user));
        }
        throw new BusinessException(Constants.VALUES.get(Constants.USER_ALREADY_EXIST));
    }

//    /**
//     * 用户注销(注:待使用token完善，先做保留)
//     *
//     * @param session
//     * @return
//     */
//    @PostMapping("/logout")
//    public ResultBean logout(HttpSession session) {
//        Object user = session.getAttribute(Constants.USER_LOGIN_SESSION_KEY);
//        if (StringUtils.isEmpty(user)) {
//            throw new BusinessException(Constants.VALUES.get(Constants.NO_USER_LOGIN));
//        }
//        session.removeAttribute(Constants.USER_LOGIN_SESSION_KEY);
//        return ResultUtils.success("login.html");
//    }

    /**
     * 修改用户密码
     *
     * @param mobile      用户手机号，唯一标识
     * @param oldPassword 用户旧密码
     * @param oldPassword 用户新密码
     * @return
     */
    @PostMapping("/changePasswd")
    public ResultBean changePasswd(@RequestParam("mobile") String mobile,
                                   @RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword) {
        //校验
        isVerifiedUserLoginInfo(mobile, oldPassword, newPassword);
        if (oldPassword.equals(newPassword)) {
            throw new BusinessException(Constants.VALUES.get(Constants.OLDPASSWORD_NOT_SAME_NEWPASSWORD));
        }
        User userFromDatabase = userService.findByMobile(mobile);
        if (!userFromDatabase.getPassword().equals(MD5Utils.string2MD5(oldPassword))) {
            throw new BusinessException(Constants.VALUES.get(Constants.OLDPASSWORD_IS_INCORRECT));
        }
        userFromDatabase.setPassword(MD5Utils.string2MD5(newPassword));
        return ResultUtils.success(userService.updateUser(userFromDatabase));
    }

    /**
     * 用户密码和手机校验
     *
     * @param mobile
     * @param passwords
     * @return
     */
    private void isVerifiedUserLoginInfo(String mobile, String... passwords) {
        if (StringUtils.isEmpty(mobile)) {
            throw new BusinessException(Constants.VALUES.get(Constants.PHONE_NUM_NOT_EMPTY));
        }
        if (!VerifyUtils.isPhoneNum(mobile)) {
            throw new BusinessException(Constants.VALUES.get(Constants.PHONE_NUM_IS_INCORRECT));
        }
        for (String password : passwords) {
            if (StringUtils.isEmpty(password)) {
                throw new BusinessException(Constants.VALUES.get(Constants.PASSWORD_IS_EMPTY));
            }
            if (!VerifyUtils.isPassword(password)) {
                throw new BusinessException(Constants.VALUES.get(Constants.PASSWORD_IS_INCORRECT));
            }
        }
    }
}
