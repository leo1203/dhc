package cn.com.dhc.roomservice.service;

import cn.com.dhc.roomservice.bean.MeetingAreaTime;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.repository.MeetingAreaTimeRepository;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class MeetingAreaTimeService {
    @Autowired
    private MeetingAreaTimeRepository meetingAreaTimeRepository;

    /*
    根据定义Id,User查询方法
     */
    public MeetingAreaTime findById(String  areaTimeId){
        if(null==meetingAreaTimeRepository.findByAreaTimeId(areaTimeId)){
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return  meetingAreaTimeRepository.findByAreaTimeId(areaTimeId);

    }
    public MeetingAreaTime findByUser(String  createUser){
        if(null==meetingAreaTimeRepository.findByCreateUser(createUser)){
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return  meetingAreaTimeRepository.findByCreateUser(createUser);

    }
    /*
     根据定义给表插入一条记录
     */
    public MeetingAreaTime insertMeet(MeetingAreaTime meetingAreaTime){
        meetingAreaTime.setCreateTime(new Date(System.currentTimeMillis()));
        return meetingAreaTimeRepository.save(meetingAreaTime);
    }
    /*
    根据Id删除一条记录
     */
    public boolean deleteMeet(String areaTimeId){
        MeetingAreaTime oldmeetingAreaTime=  meetingAreaTimeRepository.findByAreaTimeId(areaTimeId);
        if(null==oldmeetingAreaTime){
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        meetingAreaTimeRepository.delete(oldmeetingAreaTime);
        return  true;
    }
    /*
    根据Id修改一条记录
     */
    public MeetingAreaTime updateMeet(MeetingAreaTime meetingAreaTime){
        meetingAreaTime.setUpdateTime(new Date(System.currentTimeMillis()));
        MeetingAreaTime oldmeetingAreaTime = meetingAreaTimeRepository.findByAreaTimeId(meetingAreaTime.getAreaTimeId());
        if(StringUtils.isEmpty(oldmeetingAreaTime)){
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return  meetingAreaTimeRepository.saveAndFlush(meetingAreaTime);


    }
     /*
    根据逻辑delFlag删除记录
     */
    public boolean deleteMeetDelFlag(String areaTimeId) {
        MeetingAreaTime oldmeetingAreaTime = meetingAreaTimeRepository.findByAreaTimeId(areaTimeId);
        if(null==oldmeetingAreaTime){
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        oldmeetingAreaTime.setDelFlag(true);
        updateMeet(oldmeetingAreaTime);
        return true;
    }
}
