package cn.com.dhc.roomservice.service;


import cn.com.dhc.roomservice.bean.MeetingRole;
import cn.com.dhc.roomservice.bean.User;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.repository.UserRepository;
import cn.com.dhc.roomservice.utils.MD5Utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetingRoleService roleService;

    /*
      根据定义user_id,User查询方法
    */
    public User findById(String userId) {
        return userRepository.findByUserId(userId);
    }

    /*
      根据定义user_name,User查询方法
    */
    public User findByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /*
      根据定义给表插入一条记录
     */
    public JSONObject insertUser(User user) {
        User oldUser = userRepository.findByMobile(user.getMobile());
        if (!StringUtils.isEmpty(oldUser)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_ALREADY_EXIST));
        }
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setPassword(MD5Utils.string2MD5(user.getPassword()));
        User u = userRepository.save(user);
        int roleId = user.getRoleId();
        MeetingRole role = roleService.findMeetingRoleByRoleId(roleId);
        JSONObject jobj = new JSONObject();
        jobj.put("role", role);
        jobj.put("user", u);
        return jobj;
    }

    /*
     根据Id删除一条记录
    */
    public boolean deleteUser(String userId) {
        User oldUser = userRepository.findByUserId(userId);
        if (StringUtils.isEmpty(oldUser)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        userRepository.delete(oldUser);
        return true;
    }

    /*
    根据Id修改一条记录
     */
    public User updateUser(User user) {
        user.setUpdateTime(new Date(System.currentTimeMillis()));
        User oldUser = userRepository.findByUserId(user.getUserId());
        if (StringUtils.isEmpty(oldUser)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return userRepository.saveAndFlush(user);
    }

    /*
    根据逻辑delFlag删除记录
     */
    public boolean deleteUserDelFlag(String userId) {
        User oldUser = userRepository.findByUserId(userId);
        if (StringUtils.isEmpty(oldUser)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        oldUser.setDelFlag(true);
        updateUser(oldUser);
        return true;
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile) {
        User user = userRepository.findByMobile(mobile);
        if (StringUtils.isEmpty(user)) {
            throw new BusinessException(Constants.VALUES.get(Constants.USER_NOT_FOUNT));
        }
        return user;
    }

    /**
     * 获取所有用户列表
     *
     * @return
     */
    public JSONArray findAllUsers() {
        List<User> users = userRepository.findAllUsers(0);
        JSONArray objects = new JSONArray();
        for (User u : users) {
            MeetingRole role = roleService.findMeetingRoleByRoleId(u.getRoleId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", u);
            jsonObject.put("role", role);
            objects.add(jsonObject);
        }
        return objects;
    }
}
