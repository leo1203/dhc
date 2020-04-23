package cn.com.dhc.roomservice.service;

import cn.com.dhc.roomservice.bean.MeetingArea;
import cn.com.dhc.roomservice.repository.MeetingAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huleikai
 * @create 2019-04-28 16:38
 */
@Service
public class MeetingAreaService {

    @Autowired
    private MeetingAreaRepository meetingAreaRepository;

    /**
     * 根据 areaId查找场地信息
     *
     * @param areaId 场地ID
     * @return MeetingArea场地信息对象
     */
    public MeetingArea findMeetingAreaByAreaId(String areaId) {
        return meetingAreaRepository.findMeetingAreaByAreaId(areaId);
    }

    /**
     * 添加一个场地信息对象
     *
     * @param meetingArea 新增的场地信息MeetingArea对象
     * @return MeetingArea场地信息对象
     */
    public MeetingArea insertMeetingArea(MeetingArea meetingArea) {
        meetingArea.setCreateTime(new Date(System.currentTimeMillis()));
        meetingArea.setUpdateTime(new Date(System.currentTimeMillis()));
        return meetingAreaRepository.save(meetingArea);
    }

    /**
     * 根据 areaId删除场地信息
     *
     * @param areaId 场地ID
     * @return
     */
    public boolean deleteMeetingAreaByAreaId(String areaId) {
        MeetingArea meetingArea = meetingAreaRepository.findMeetingAreaByAreaId(areaId);
        meetingArea.setDel_flag(true);
        return updateMeetingAreaById(meetingArea);
    }

    /**
     * 根据 Id更新场地信息
     *
     * @param meetingArea 要更新的场地信息对象
     * @return
     */
    public boolean updateMeetingAreaById(MeetingArea meetingArea) {
        boolean flag = meetingAreaRepository.existsById(meetingArea.getAreaId());
        if (flag) {
            //存在则更新
            meetingArea.setUpdateTime(new Date(System.currentTimeMillis()));
            meetingAreaRepository.saveAndFlush(meetingArea);
            return true;
        }
        return false;
    }

    /**
     * 获取所有的会议室
     *
     * @return 会议室列表
     */
    public List<MeetingArea> findAll() {
        return meetingAreaRepository.findAllRooms(false);
    }
}
