package cn.com.dhc.roomservice.service;

import cn.com.dhc.roomservice.bean.MeetingRole;
import cn.com.dhc.roomservice.repository.MeetingRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huleikai
 * @create 2019-04-29 15:55
 */
@Service
public class MeetingRoleService {
    @Autowired
    private MeetingRoleRepository meetingRoleRepository;

    public MeetingRole insertMeetingRole(MeetingRole meetingRole) {
        meetingRole.setCreateTime(new Date(System.currentTimeMillis()));
        meetingRole.setUpdateTime(new Date(System.currentTimeMillis()));
        return meetingRoleRepository.save(meetingRole);
    }

    public MeetingRole findMeetingRoleByRoleId(int roleId) {
        return meetingRoleRepository.getOne(roleId);
    }

    public void deleteMeetingRoleByRoleId(int roleId) {
        MeetingRole meetingRole = findMeetingRoleByRoleId(roleId);
        meetingRole.setDel_flag(true);
        updateMeetingRoleByRoleId(meetingRole);
    }

    public boolean updateMeetingRoleByRoleId(MeetingRole meetingRole) {
        boolean flag = meetingRoleRepository.existsById(meetingRole.getRoleId());
        if (flag) {
            //存在则更新
            meetingRole.setUpdateTime(new Date(System.currentTimeMillis()));
            meetingRoleRepository.saveAndFlush(meetingRole);
            return true;
        }
        return false;
    }

    /**
     * 获取所有权限类型
     *
     * @return
     */
    public List<MeetingRole> findAllRoles() {
        return meetingRoleRepository.findAllRooms(0);
    }
}
