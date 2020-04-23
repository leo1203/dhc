package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.MeetingAreaTime;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.service.MeetingAreaTimeService;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/meetingAreaTime")
public class MeetingAreaTimeController {
    @Autowired
    private MeetingAreaTimeService areaTimeService;
    /*
     * 根据名字查找
     */
    @PostMapping(value = "/findByCreateUser")
    public ResultBean findByUser_name(@RequestParam("createUser") String createUser) {
        if(null==areaTimeService.findByUser(createUser)){
            throw  new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return ResultUtils.success(areaTimeService.findByUser(createUser));
    }
    /*
     * 根据ID查找
     */
    @PostMapping(value = "/findByAreaTimeId")
    public ResultBean findByCreate_user(@RequestParam("areaTimeId") String areaTimeId) {
        if(null==areaTimeService.findById(areaTimeId)){
            throw  new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return ResultUtils.success(areaTimeService.findById(areaTimeId));
    }
    /*
       插入记录数
     */
    @PostMapping(value = "/insertMeet")
    public ResultBean createMeet(MeetingAreaTime meetingAreaTime) {
        return ResultUtils.success(areaTimeService.insertMeet(meetingAreaTime));
    }
    /*
       删除记录
     */
    @PostMapping (value = "/deleteMeet")
    public ResultBean deleteMeet(String areaTimeId) {
        if(null==areaTimeService.findById(areaTimeId)){
            throw  new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return ResultUtils.success(areaTimeService.deleteMeet(areaTimeId));
    }

    /*
       修改记录
     */
    @PostMapping (value = "/updateMeet")
    public ResultBean updateMeet(MeetingAreaTime meetingAreaTime) {
        return ResultUtils.success(areaTimeService.updateMeet(meetingAreaTime));
    }
    /*
        逻辑delflag删除
     */
    @PostMapping (value = "/deleteMeetDelFlag")
    public ResultBean deleteMeetDelFlag(String areaTimeId) {
        if(null==areaTimeService.findById(areaTimeId)){
            throw  new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_TIME_NOT_FOUND));
        }
        return ResultUtils.success(areaTimeService.deleteMeetDelFlag(areaTimeId));
    }

}
