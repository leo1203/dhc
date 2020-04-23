package cn.com.dhc.roomservice.controller;

import cn.com.dhc.roomservice.bean.MeetingArea;
import cn.com.dhc.roomservice.bean.vo.ResultBean;
import cn.com.dhc.roomservice.common.Constants;
import cn.com.dhc.roomservice.exception.BusinessException;
import cn.com.dhc.roomservice.service.MeetingAreaService;
import cn.com.dhc.roomservice.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huleikai
 * @create 2019-04-28 16:48
 */
@RestController
@RequestMapping(value = "/room")
public class MeetingAreaController {

    @Autowired
    private MeetingAreaService meetingAreaService;

    /**
     * 插入一个MeetingArea对象实例
     *
     * @param meetingArea 要添加的MeetingArea对象
     * @return
     */
    @PostMapping("/createRoom")
    public ResultBean createRoom(@Validated MeetingArea meetingArea, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(meetingAreaService.insertMeetingArea(meetingArea));
    }

    @PostMapping("/listRooms")
    public ResultBean listRooms() {
        return ResultUtils.success(meetingAreaService.findAll());
    }

    /**
     * 根据 areaId查找场地信息
     *
     * @param areaId 场地ID
     * @return
     */
    @GetMapping("/findByAreaId/{areaId}")
    public ResultBean findByAreaId(@PathVariable String areaId) {
        if (null == meetingAreaService.findMeetingAreaByAreaId(areaId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_NOT_FOUND));
        }
        return ResultUtils.success(meetingAreaService.findMeetingAreaByAreaId(areaId));
    }

    /**
     * 根据 areaId删除场地信息
     *
     * @param areaId 场地ID
     * @return
     */
    @DeleteMapping("/deleteRoom/{areaId}")
    public ResultBean deleteRoom(@PathVariable String areaId) {
        if (null == meetingAreaService.findMeetingAreaByAreaId(areaId)) {
            throw new BusinessException(Constants.VALUES.get(Constants.MEETING_AREA_NOT_FOUND));
        }
        return ResultUtils.success(meetingAreaService.deleteMeetingAreaByAreaId(areaId));
    }

    /**
     * 根据 Id更新场地信息
     *
     * @param meetingArea 要更新的场地信息对象
     * @return
     */
    @PutMapping("/updateMeetingAreaById")
    public ResultBean updateMeetingAreaById(MeetingArea meetingArea) {
        boolean flag = meetingAreaService.updateMeetingAreaById(meetingArea);
        if (flag) {
            return ResultUtils.success(meetingAreaService.updateMeetingAreaById(meetingArea));
        }
        return ResultUtils.failure(Constants.VALUES.get(Constants.MEETING_AREA_NOT_FOUND));

    }
}
