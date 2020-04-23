package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.MeetingRole;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.service.MeetingRoleService;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huleikai
 * @create 2019-04-29 15:53
 */
@RestController
@RequestMapping("/role")
public class MeetingRoleController {
    @Autowired
    private MeetingRoleService meetingRoleService;

    /**
     * 插入一个MeetingRole对象实例
     *
     * @param meetingRole 要添加的MeetingRole对象
     * @return
     */
    @PostMapping("/createRole")
    public ResultBean createMeetingRole(@Validated MeetingRole meetingRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(meetingRoleService.insertMeetingRole(meetingRole));
    }

    @GetMapping("/listRoles")
    public ResultBean listRoles() {
        return ResultUtils.success(meetingRoleService.findAllRoles());
    }

    /**
     * 根据 roleId查找角色信息
     *
     * @param roleId 角色ID
     * @return
     */
    @GetMapping("/findMeetingRoleByRoleId/{roleId}")
    public ResultBean findMeetingRoleByRoleId(@PathVariable int roleId) {
        return ResultUtils.success(meetingRoleService.findMeetingRoleByRoleId(roleId));
    }

    /**
     * 根据 roleId删除角色信息
     *
     * @param roleId 角色ID
     * @return
     */
    @DeleteMapping("/deleteMeetingRoleByRoleId/{roleId}")
    public ResultBean deleteMeetingRoleByRoleId(@PathVariable int roleId) {
        meetingRoleService.deleteMeetingRoleByRoleId(roleId);
        return ResultUtils.success();
    }

    /**
     * 根据 Id更新角色信息
     *
     * @param meetingRole 要更新的角色信息对象
     * @return
     */
    @PutMapping("/updateMeetingRoleByRoleId")
    public ResultBean updateMeetingRoleByRoleId(MeetingRole meetingRole) {
        boolean flag = meetingRoleService.updateMeetingRoleByRoleId(meetingRole);
        if (flag) {
            return ResultUtils.success(meetingRoleService.updateMeetingRoleByRoleId(meetingRole));
        } else {
            return ResultUtils.failure("找不到该角色信息记录,无法更新");
        }
    }
}
